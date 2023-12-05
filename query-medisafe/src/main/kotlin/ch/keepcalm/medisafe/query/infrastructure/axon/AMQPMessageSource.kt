package ch.keepcalm.medisafe.query.infrastructure.axon

import ch.keepcalm.medisafe.query.domain.SafeCreatedEvent
import ch.keepcalm.medisafe.query.infrastructure.logger
import com.rabbitmq.client.Channel
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Configuration
class AxonConfig {

    @Autowired
    private lateinit var amqpMessageSource: SpringAMQPMessageSource

    @Autowired
    fun configure(eventProcessingConfigurer: EventProcessingConfigurer) {
        eventProcessingConfigurer.registerSubscribingEventProcessor("amqpEvents") { _ -> amqpMessageSource }
    }
}

@Component
class AMQPMessageSource(messageConverter: AMQPMessageConverter) : SpringAMQPMessageSource(messageConverter) {

    companion object {
        /**
         * The name of the queue used for consuming events.
         */
        const val QUEUE_NAME = "SafeCreatedEvent"
    }

    @RabbitListener(queues = [QUEUE_NAME])
    override fun onMessage(message: Message?, channel: Channel?) {
        logger.info { "::--> AMQP event: $message received"}
        super.onMessage(message, channel)
    }
}
@ProcessingGroup(value = "amqpEvents")
class SafeEventHandler {

    @EventHandler
    fun on(event: SafeCreatedEvent) {
        // handle the event
        logger.info { "::--> SafeEventHandler  Handling event: ${this.javaClass}"}

    }
}
