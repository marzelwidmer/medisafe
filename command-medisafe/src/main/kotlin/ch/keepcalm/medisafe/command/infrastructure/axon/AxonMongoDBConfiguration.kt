package ch.keepcalm.medisafe.command.infrastructure.axon

import com.mongodb.client.MongoClient
import com.thoughtworks.xstream.XStream
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.xml.XStreamSerializer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


/**
 * A utility object for creating a secure XStream instance.
 */
object SecureXStreamSerializer {
    /**
     * Creates a secure XStream instance.
     *
     * The created XStream instance is configured to use the class loader of the SecureXStreamSerializer class.
     * It's also configured to allow types in the "org.axonframework" package and all other packages.
     *
     * @return a secure XStream instance.
     */
    fun xStream(): XStream {
        val xStream = XStream()
        xStream.classLoader = SecureXStreamSerializer::class.java.classLoader
        xStream.allowTypesByWildcard(
            arrayOf(
                "org.axonframework.**",
                "**",
            )
        )
        return XStreamSerializer.builder().xStream(xStream).build().xStream
    }
}


@Component
class AxonMongoDBConfiguration {

    companion object {
        /**
         * The name of the MongoDB database used for the event store and token store.
         */
        const val DATABASE_NAME = "eventstore"
    }

    /**
     * Creates a Mongo-based Event Storage Engine.
     *
     * @param client the MongoClient to connect to MongoDB.
     * @return a MongoEventStorageEngine that uses the provided MongoClient.
     */
    @Bean
    fun storageEngine(client: MongoClient?): EventStorageEngine? {
        return MongoEventStorageEngine.builder()
            .eventSerializer(
                XStreamSerializer.builder()
                    .xStream(SecureXStreamSerializer.xStream())
                    .build()
            )
            .snapshotSerializer(
                XStreamSerializer.builder()
                    .xStream(SecureXStreamSerializer.xStream())
                    .build()
            )
            .mongoTemplate(
                DefaultMongoTemplate
                    .builder()
                    .mongoDatabase(client, DATABASE_NAME)
                    .build()
            ).build()
    }

    /**
     * Creates a MongoTokenStore for storing tracking tokens.
     *
     * @param client the MongoClient to connect to MongoDB.
     * @param serializer the Serializer to serialize and deserialize the tokens.
     * @return a MongoTokenStore that uses the provided MongoClient and Serializer.
     */
    @Bean
    fun tokenStore(client: MongoClient, serializer: Serializer): TokenStore {
        return MongoTokenStore.builder()
            .mongoTemplate(
                DefaultMongoTemplate.builder()
                    .mongoDatabase(client, DATABASE_NAME)
                    .build())
            .serializer(serializer)
            .build()
    }
}
