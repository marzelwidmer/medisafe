package ch.keepcalm.medisafe.command

import ch.keepcalm.medisafe.command.infrastructure.axon.AxonSnapshotThresholdConfigurer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AxonSnapshotThresholdConfigurer::class)
class CommandMedisafeApplication

fun main(args: Array<String>) {
	runApplication<CommandMedisafeApplication>(*args)
}
