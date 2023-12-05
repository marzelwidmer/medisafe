package ch.keepcalm.medisafe.query.application

import ch.keepcalm.medisafe.query.domain.SafeCreatedEvent
import ch.keepcalm.medisafe.query.infrastructure.logger
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class SafeEventHandler {

    @EventHandler
    fun on(event: SafeCreatedEvent) {
        // handle the event
        logger.info { "::--> SafeEventHandler  Handling event: ${this.javaClass}"}

    }

   /* *//**
     * Check account balance.
     *//*
    @QueryHandler
    fun on(query: AccountBalanceQuery): Long? {
        logger.info { "received query ${query.bankAccountId}" }
        return accountAmount[query.bankAccountId]
    }*/
}
