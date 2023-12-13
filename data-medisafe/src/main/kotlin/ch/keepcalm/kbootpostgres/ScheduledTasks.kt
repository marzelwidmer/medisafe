package ch.keepcalm.kbootpostgres

import ch.keepcalm.kbootpostgres.rest.PostService
import ch.keepcalm.kbootpostgres.store.AlbumRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


const val SCHEDULE_TIME = 30000L // 30 Sec


@Component
@EnableScheduling
class ScheduledTasks(private val albumRepository: AlbumRepository, private val postService: PostService) {

    @OptIn(DelicateCoroutinesApi::class)
    @Scheduled(fixedRate = SCHEDULE_TIME)
    fun fixedRateScheduledTask() {
        GlobalScope.launch {
            println("Found ${albumRepository.findAll().count()} - records")
            albumRepository.findAll().forEach { album ->
                println("Album Id: ${album.albumId}")
                println("UserId: ${album.userId}")
                while (isActive) { // checks if the coroutine is still active
                    val aPostForUserId = postService.getAPostForUserId(userId = album.userId)
                    println("--------------------->>>>>>>>>>>>>>>>>>>   $aPostForUserId <<<<<<<<<<<<<<<<<<<<<<<<------------------")
                }
            }
        }
    }
}
