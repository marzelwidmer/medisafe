package command.infrastructure.axon

import command.infrastructure.logger
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class AxonEventLogger {

    @EventHandler
    fun on(any: Any) {
        logger.info { "::--> Handling event: ${any.javaClass}"}
    }
}
