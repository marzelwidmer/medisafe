package ch.keepcalm.medisafe.command.infrastructure.axon
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class AMQPMessageSource {

    companion object {
        const val TOPIC_EXCHANGE_NAME = "SafeCreatedEvent"
        const val QUEUE_NAME = "SafeCreatedEvent"
    }
    @Bean
    fun eventsExchange(): TopicExchange {
        return TopicExchange(TOPIC_EXCHANGE_NAME)
    }

    @Bean
    fun eventsQueue(): Queue {
        return Queue(QUEUE_NAME, true)
    }

    @Bean
    fun eventsBinding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue)
            .to(exchange)
            .with("#")
    }



}

//
//@Component
//class AMQPMessageSource {
//
//    companion object {
//        /**
//         * The name of the topic exchange used for publishing events.
//         */
//        const val TOPIC_EXCHANGE_NAME = "SafeCreatedEvent"
//
//    }
//
//    /**
//     * Creates a TopicExchange with the name specified in TOPIC_EXCHANGE_NAME.
//     *
//     * @return a TopicExchange for publishing events.
//     */
//    @Bean
//    fun eventsExchange(): TopicExchange {
//        return TopicExchange(TOPIC_EXCHANGE_NAME)
//    }
//}
