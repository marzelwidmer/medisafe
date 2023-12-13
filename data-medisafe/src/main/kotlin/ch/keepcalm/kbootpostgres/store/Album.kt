package ch.keepcalm.kbootpostgres.store

import jakarta.validation.constraints.NotEmpty
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.util.*

class Album(
    @Id
    val id: UUID,
    @NotEmpty
    val userId: Int,
    @NotEmpty
    val albumId: Int,
    @Version val version: Int?
)
