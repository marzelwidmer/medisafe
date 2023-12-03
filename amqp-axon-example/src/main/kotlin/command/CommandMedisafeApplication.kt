package command

import command.infrastructure.axon.AxonSnapshotThresholdConfigurer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableConfigurationProperties(AxonSnapshotThresholdConfigurer::class)
@EnableScheduling
class CommandMedisafeApplication

fun main(args: Array<String>) {
	runApplication<CommandMedisafeApplication>(*args)
}
