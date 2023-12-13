package ch.keepcalm.kbootpostgres.store

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface AlbumRepository : CrudRepository<Album, UUID>
