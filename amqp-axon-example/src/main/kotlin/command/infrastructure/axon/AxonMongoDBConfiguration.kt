package command.infrastructure.axon

import com.mongodb.client.MongoClient
import com.rabbitmq.client.Channel
import com.thoughtworks.xstream.XStream
import org.axonframework.config.Configurer
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventhandling.tokenstore.inmemory.InMemoryTokenStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.xml.XStreamSerializer
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


object SecureXStreamSerializer {
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

    /**
     * The SpringAMQPMessageSource allows event processors to read messages from a queue instead of the event store or
     * event bus. It acts as an adapter between Spring AMQP and the SubscribableMessageSource needed by these processors.
     *
     * @param messageConverter Converter to/from AMQP Messages to/from Axon Messages.
     */
    @Bean
    fun amqpMessageSource(messageConverter: AMQPMessageConverter): SpringAMQPMessageSource {
        return object : SpringAMQPMessageSource(messageConverter) {
            @RabbitListener(queues = [AMQPAxonExampleApplication.QUEUE_NAME])
            override fun onMessage(message: Message?, channel: Channel?) {
                println("amqp event $message received")
                super.onMessage(message, channel)
            }
        }
    }

    /**
     * Creates an InMemoryEventStorageEngine.
     * NOT PRODUCTION READY
     */
    @Bean
    fun storageEngine(): EventStorageEngine = InMemoryEventStorageEngine()

    /**
     * Creates an InMemoryTokenStore.
     * NOT PRODUCTION READY
     */
    @Bean
    fun tokenStore(): TokenStore = InMemoryTokenStore()


    companion object {
        const val DATABASE_NAME = "eventstore"
    }

    /**
     *
     * Create a Mongo based Event Storage Engine.
     */
//    @Bean
    fun storageEngine(client: MongoadadClient?): EventStorageEngine? {
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
     * Uses the Configurer to wire everything together including Mongo as the Event and Token Store.
     */
//    @Autowired
    fun configuration(configurer: Configurer, client: MongoClient) {
        configurer
            .configureEmbeddedEventStore {
                storageEngine(client)
            }
            .eventProcessing { conf -> conf.registerTokenStore { tokenStore(client, it.serializer()) } }
    }


    /**
     * Create a Mongo based
     * Token Store.
     */
    fun tokenStore(client: MongoClient, serializer: Serializer): TokenStore = MongoTokenStore.builder()
        .mongoTemplate(
            DefaultMongoTemplate.builder()
                .mongoDatabase(client, DATABASE_NAME)
                .build()
        )
        .serializer(serializer)
        .build()

}
