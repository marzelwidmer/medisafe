package ch.keepcalm.kbootpostgres.store

import ch.keepcalm.kbootpostgres.SCHEDULE_TIME
import kotlinx.coroutines.*
import java.util.*
import kotlin.random.Random

@OptIn(DelicateCoroutinesApi::class)
fun scheduledNewDataInDatabaseTask(repository: AlbumRepository) {
    val job = GlobalScope.launch {
        while (isActive) { // checks if the coroutine is still active
            println("Task running...(${SCHEDULE_TIME / 1000} seconds)")

            val randomUserId = Random.nextInt(1, 11)
            val randomAlbumId = Random.nextInt(1, 3)

            println("----->>> Save random entry in database [userId=${randomUserId}, albumId=${randomAlbumId}]")
            repository.deleteAll()

            val albumList = listOf(Album(UUID.randomUUID(), userId = randomUserId, albumId = randomAlbumId, version = null))
            repository.saveAll(albumList)

            delay(SCHEDULE_TIME) // Delay for 30 seconds
        }
    }

    // Cancel the job after a minute
    GlobalScope.launch {
        delay(SCHEDULE_TIME * 2) // let it run for a minute
        job.cancel() // cancels the job
    }
}
