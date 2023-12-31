package ch.keepcalm.medisafe.query.infrastructure.axon

import ch.keepcalm.medisafe.query.domain.SafeCreatedEvent
import ch.keepcalm.medisafe.query.infrastructure.logger
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@ProcessingGroup(value = "amqpEvents")
@Component
class SafeEventHandler {

    @EventHandler
    fun on(event: SafeCreatedEvent) {
        // handle the event
        println(event)
        logger.info { "::--> SafeEventHandler  Handling event: ${this.javaClass}" }
    }


}
