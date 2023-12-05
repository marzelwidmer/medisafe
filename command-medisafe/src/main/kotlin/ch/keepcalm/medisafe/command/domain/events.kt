package ch.keepcalm.medisafe.command.domain

import org.axonframework.modelling.command.AggregateIdentifier

// TODO: 02.12.2023 remove Axon stuff
data class SafeCreatedEvent(
        @AggregateIdentifier
        val safeId: SafeId,
        val name: String
)
