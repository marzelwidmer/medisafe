//package ch.keepcalm.medisafe.command.infrastructure.axon
//
//
//import com.rabbitmq.client.Channel
//import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter
//import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
//import org.springframework.amqp.core.*
//import org.springframework.amqp.rabbit.annotation.RabbitListener
//import org.springframework.context.annotation.Bean
//import org.springframework.stereotype.Component
//
//
//@Component
//class AMQPMessageSource {
//
//    @Bean
//    fun eventsExchange(): TopicExchange {
//        return TopicExchange(TOPIC_EXCHANGE_NAME)
//    }
//
//    @Bean
//    fun eventsQueue(): Queue {
//        return Queue(QUEUE_NAME, true)
//    }
//
//    @Bean
//    fun eventsBinding(queue: Queue, exchange: TopicExchange): Binding {
//        return BindingBuilder.bind(queue)
//            .to(exchange)
//            .with("#")
//    }
//
//    companion object {
//
//        const val TOPIC_EXCHANGE_NAME = "SafeCreatedEvent"
//
//        const val QUEUE_NAME = "SafeCreatedEvent"
//    }
//
//    /**
//     * The SpringAMQPMessageSource allows event processors to read messages from a queue instead of the event store or
//     * event bus. It acts as an adapter between Spring AMQP and the SubscribableMessageSource needed by these processors.
//     *
//     * @param messageConverter Converter to/from AMQP Messages to/from Axon Messages.
//     */
//    @Bean
//    fun amqpMessageSource(messageConverter: AMQPMessageConverter): SpringAMQPMessageSource {
//        return object : SpringAMQPMessageSource(messageConverter) {
//            @RabbitListener(queues = [QUEUE_NAME])
//            override fun onMessage(message: Message?, channel: Channel?) {
//                println("----------->  amqp event $message received")
//                super.onMessage(message, channel)
//            }
//        }
//    }
//
//
//    /*
//    @Bean
//    fun myMessageSource(serializer: Serializer?): SpringAMQPMessageSource {
//        return object : SpringAMQPMessageSource(serializer) {
//            @RabbitListener(queues = ["SafeCreatedEvent"])
//            override fun onMessage(message: Message, channel: Channel?) {
//                super.onMessage(message, channel)
//            }
//        }
//    }
//
//    @Bean
//    fun amqpMessageConverter(serializer: Serializer): AMQPMessageConverter {
//        return DefaultAMQPMessageConverter.builder()
//            .serializer(serializer)
//            .build()
//    }
//    @Bean
//    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
//        val rabbitTemplate = RabbitTemplate(connectionFactory)
//        rabbitTemplate.messageConverter = SimpleMessageConverter()
//        return rabbitTemplate
//    }
//
//    @Bean
//    fun springAMQPPublisher(eventBus: EventBus): SpringAMQPPublisher {
//        val publisher = SpringAMQPPublisher(eventBus)
//        publisher.setExchangeName("SafeCreatedEvent")
//        publisher.setRoutingKeyResolver(PackageRoutingKeyResolver())
//        return publisher
//    }
//
//    @Bean
//    fun rabbitAdmin(connectionFactory: ConnectionFactory): RabbitAdmin {
//        return RabbitAdmin(connectionFactory).apply {
//            declareQueue(Queue("SafeCreatedEvent"))
//        }
//    }*/
//}
