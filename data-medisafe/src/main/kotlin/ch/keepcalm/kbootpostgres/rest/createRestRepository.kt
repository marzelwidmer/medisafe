package ch.keepcalm.kbootpostgres.rest

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.createClient
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

fun createRestRepository(baseUrl: String = "https://jsonplaceholder.typicode.com"): PostRestRepository {
    val client = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()
    return HttpServiceProxyFactory
        .builderFor(WebClientAdapter.create(client))
        .build().createClient()
}
