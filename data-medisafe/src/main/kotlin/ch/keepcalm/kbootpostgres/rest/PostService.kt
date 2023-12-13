package ch.keepcalm.kbootpostgres.rest


import org.springframework.stereotype.Service


@Service
class PostService(private val postRestRepository: PostRestRepository) {
    fun getAPostForUserId(userId: Int): List<PostResponse> {
        return postRestRepository.findAllAlbumsForUser(userId = userId)
    }
}


