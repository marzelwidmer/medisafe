package ch.keepcalm.medisafe.query.infrastructure.axon

import ch.keepcalm.medisafe.query.infrastructure.logger
import com.rabbitmq.client.Channel
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class AMQPMessageSource {

    companion object {
        /**
         * The name of the queue used for consuming events.
         */
        const val QUEUE_NAME = "SafeCreatedEvent"
    }

    /**
     * The SpringAMQPMessageSource allows event processors to read messages from a queue instead of
     * the event store or event bus. It acts as an adapter between Spring AMQP and the
     * SubscribableMessageSource needed by these processors.
     *
     * @param messageConverter Converter to/from AMQP Messages to/from Axon Messages.
     */
    @Bean
    fun amqpMessageSource(messageConverter: AMQPMessageConverter): SpringAMQPMessageSource {
        return object : SpringAMQPMessageSource(messageConverter) {
            @RabbitListener(queues = [QUEUE_NAME])
            override fun onMessage(message: Message?, channel: Channel?) {
                logger.info { "::--> AMQP event: $message received"}
                super.onMessage(message, channel)
            }
        }
    }
}
