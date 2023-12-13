package ch.keepcalm.kbootpostgres


import ch.keepcalm.kbootpostgres.rest.createRestRepository
import ch.keepcalm.kbootpostgres.store.AlbumRepository
import ch.keepcalm.kbootpostgres.store.scheduledNewDataInDatabaseTask
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans


@SpringBootApplication
class KbootPostgressApplication



fun main(args: Array<String>) {
    runApplication<KbootPostgressApplication>(*args) {
        addInitializers(
            beans {
                bean("createRestRepository") {
                    createRestRepository()
                }
                bean {
                    val repository = ref<AlbumRepository>()
                    ApplicationRunner {
                        println("ApplicationRunner ----------------->")
                        scheduledNewDataInDatabaseTask(repository)
                    }
                }
            }
        )
    }
}





