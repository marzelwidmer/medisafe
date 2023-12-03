package ch.keepcalm.medisafe.query.infrastructure.axon

import org.axonframework.config.EventProcessingConfiguration
import org.axonframework.eventhandling.StreamingEventProcessor
import org.springframework.stereotype.Service
import java.time.Instant

// https://developer.axoniq.io/w/demystifying-tracking-event-processors-in-axon-framework
// https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors#assigning-handlers-to-processors
// https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors/streaming#replay-api
// https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors/streaming#triggering-a-reset
// https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors/streaming

@Service
class AxonEventProcessorService(private val processingConfiguration: EventProcessingConfiguration) {

    fun resetTokensFor(processorName: String, since: Instant?) {
        processingConfiguration.eventProcessor(processorName, StreamingEventProcessor::class.java)
            .ifPresent { streamingEventProcessor: StreamingEventProcessor ->
                streamingEventProcessor.shutDown()
                // reset the tokens to prepare the processor
                if (since != null) {
                    streamingEventProcessor.resetTokens { it.createTokenAt(since) }
                } else {
                    streamingEventProcessor.resetTokens()
                }
                streamingEventProcessor.start()
            }
    }
}

