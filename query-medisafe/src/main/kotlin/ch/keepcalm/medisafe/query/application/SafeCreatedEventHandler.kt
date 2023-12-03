package ch.keepcalm.medisafe.query.application

import ch.keepcalm.medisafe.query.domain.SafeCreatedEvent
import ch.keepcalm.medisafe.query.infrastructure.logger
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class SafeCreatedEventHandler {

    @EventHandler
    fun on(event: SafeCreatedEvent) {
        // handle the event
        logger.info { "::--> SafeCreatedEventHandler  Handling event: ${this.javaClass}"}

    }
}
