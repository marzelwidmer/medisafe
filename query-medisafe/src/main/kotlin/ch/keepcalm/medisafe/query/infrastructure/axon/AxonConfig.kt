package ch.keepcalm.medisafe.query.infrastructure.axon

import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig {

    @Autowired private lateinit var amqpMessageSource: SpringAMQPMessageSource

    @Autowired
    fun configure(eventProcessingConfigurer: EventProcessingConfigurer) {
        eventProcessingConfigurer.registerSubscribingEventProcessor("amqpEvents") { _ ->
            amqpMessageSource
        }
    }
}
