package ch.keepcalm.medisafe.command.infrastructure.axon
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class AMQPMessageSource {

    companion object {
        /**
         * The name of the topic exchange used for publishing events.
         */
        const val TOPIC_EXCHANGE_NAME = "SafeCreatedEvent"
    }

    /**
     * Creates a TopicExchange with the name specified in TOPIC_EXCHANGE_NAME.
     *
     * @return a TopicExchange for publishing events.
     */
    @Bean
    fun eventsExchange(): TopicExchange {
        return TopicExchange(TOPIC_EXCHANGE_NAME)
    }
}
