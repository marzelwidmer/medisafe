package ch.keepcalm.medisafe.query.domain

import org.axonframework.modelling.command.AggregateIdentifier

//data class MediCreatedEvent(
//        val mediId: MediId,
//        val title: Title,
//)


// TODO: 02.12.2023 remove Axon stuff
data class SafeCreatedEvent(
        @AggregateIdentifier
        val safeId: SafeId,
        val name: String
)
