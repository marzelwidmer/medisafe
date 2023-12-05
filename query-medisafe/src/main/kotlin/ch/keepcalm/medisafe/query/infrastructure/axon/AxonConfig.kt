package ch.keepcalm.medisafe.query.infrastructure.axon

import org.axonframework.config.EventProcessingConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

//@Configuration
//class AxonConfig(private val amqpMessageSource: AMQPMessageSource) {
//
//    @Autowired
//    fun configure(eventProcessingConfigurer: EventProcessingConfigurer) {
//        eventProcessingConfigurer.registerSubscribingEventProcessor("myProcessor") { _ -> amqpMessageSource }
//    }
//}
