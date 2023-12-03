package ch.keepcalm.medisafe.query

import ch.keepcalm.medisafe.query.infrastructure.axon.AxonSnapshotThresholdConfigurer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AxonSnapshotThresholdConfigurer::class)
class QueryMedisafeApplication

fun main(args: Array<String>) {
	runApplication<QueryMedisafeApplication>(*args)
}
