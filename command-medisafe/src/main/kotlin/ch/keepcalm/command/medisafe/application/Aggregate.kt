package ch.keepcalm.command.medisafe.application

import ch.keepcalm.command.medisafe.domain.SafeCreatedEvent
import ch.keepcalm.command.medisafe.domain.SafeId
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.common.IdentifierFactory
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class Aggregate(){

    @AggregateIdentifier
    private lateinit var safeId: SafeId

    @CommandHandler
    constructor(command: CreateSafeCommand) : this() {
        // Validate the command

        // Generate an event
        val aggregateId = IdentifierFactory.getInstance().generateIdentifier()

        AggregateLifecycle.apply(
            SafeCreatedEvent(
                safeId = SafeId(value = aggregateId),
                name = command.name
            )
        )

    }


    @EventSourcingHandler
    fun handle(event: SafeCreatedEvent) {
        this.safeId = event.safeId
    }
}
