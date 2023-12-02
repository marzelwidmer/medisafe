package ch.keepcalm.command.medisafe.presentation.api

import ch.keepcalm.command.medisafe.application.CreateSafeCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
class SafeController (
    private val commandGateway: CommandGateway
){

    @PostMapping("/safe")
    fun createSafe(@RequestBody payload: CreateSafePayload): CompletableFuture<String> {
        val command = CreateSafeCommand(name = payload.name)
        return commandGateway.send(command)
    }
}
data class CreateSafePayload(val name: String)
