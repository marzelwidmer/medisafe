package ch.keepcalm.kbootpostgres

import ch.keepcalm.kbootpostgres.store.Album
import ch.keepcalm.kbootpostgres.store.AlbumRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class PostgresTestContainerTests(
    @Autowired private val repository: AlbumRepository,
) {

    companion object {
        @Container
        @ServiceConnection
        private val postgres: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:16.1")
    }


   @BeforeEach
    fun setUp() {
       val albumList = listOf(Album(id = UUID.randomUUID(), userId = 2, albumId = 1, version = null))
       repository.saveAll(albumList)
    }

    @Test
    fun `connection established`() {
        assertThat(postgres.isCreated).isTrue()
        assertThat(postgres.isRunning).isTrue()
        println(postgres.databaseName)
    }

    // https://reflectoring.io/assertj-lists/
    @Test
    fun `check if something in list of a Posts with filtering`() {
        assertThat(repository.findAll()).filteredOn {
            album ->
            album.userId == 2
        }
    }
}

