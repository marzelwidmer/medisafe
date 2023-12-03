package ch.keepcalm.medisafe.command.infrastructure.axon

import com.rabbitmq.client.Channel
import org.axonframework.eventhandling.EventBus
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter
import org.axonframework.extensions.amqp.eventhandling.DefaultAMQPMessageConverter
import org.axonframework.extensions.amqp.eventhandling.PackageRoutingKeyResolver
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPPublisher
import org.axonframework.serialization.Serializer
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.SimpleMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
class AMQPMessageSource {
    @Bean
    fun myMessageSource(serializer: Serializer?): SpringAMQPMessageSource {
        return object : SpringAMQPMessageSource(serializer) {
            @RabbitListener(queues = ["myQueue"])
            override fun onMessage(message: Message, channel: Channel?) {
                super.onMessage(message, channel)
            }
        }
    }

    @Bean
    fun amqpMessageConverter(serializer: Serializer): AMQPMessageConverter {
        return DefaultAMQPMessageConverter.builder()
            .serializer(serializer)
            .build()
    }
    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = SimpleMessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun springAMQPPublisher(eventBus: EventBus): SpringAMQPPublisher {
        val publisher = SpringAMQPPublisher(eventBus)
        publisher.setExchangeName("myExchange")
        publisher.setRoutingKeyResolver(PackageRoutingKeyResolver())
        return publisher
    }

    @Bean
    fun rabbitAdmin(connectionFactory: ConnectionFactory): RabbitAdmin {
        return RabbitAdmin(connectionFactory).apply {
            declareQueue(Queue("myQueue"))
        }
    }
}
