package ch.keepcalm.command.medisafe

import ch.keepcalm.command.medisafe.infrastructure.axon.AxonSnapshotThresholdConfigurer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AxonSnapshotThresholdConfigurer::class)
class CommandMedisafeApplication

fun main(args: Array<String>) {
	runApplication<CommandMedisafeApplication>(*args)
}
