package ch.keepcalm.kbootpostgres.rest

import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange

interface PostRestRepository {

    // https://jsonplaceholder.typicode.com/albums/?userId=2
    @GetExchange("/albums")
    fun findAllAlbumsForUser(@RequestParam userId: Int): List<PostResponse>
}
