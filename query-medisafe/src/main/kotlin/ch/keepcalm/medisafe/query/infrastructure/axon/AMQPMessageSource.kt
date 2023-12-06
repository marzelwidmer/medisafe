package ch.keepcalm.medisafe.query.infrastructure.axon

import ch.keepcalm.medisafe.query.domain.SafeCreatedEvent
import ch.keepcalm.medisafe.query.infrastructure.logger
import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Channel
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class AMQPMessageSource(messageConverter: AMQPMessageConverter) :
    SpringAMQPMessageSource(messageConverter) {

    companion object {
        /** The name of the queue used for consuming events. */
        const val QUEUE_NAME = "SafeCreatedEvent"
    }

    @RabbitListener(queues = [QUEUE_NAME])
    override fun onMessage(message: Message?, channel: Channel?) {
        val objectMapper = ObjectMapper() // create a new ObjectMapper
        val event = objectMapper.readValue(message?.body, SafeCreatedEvent::class.java) // deserialize the byte array
        println(event.safeId) // print the deserialized event
        println(event.name) // print the deserialized event
        logger.info { "::--> AMQP event: $message received" }
        super.onMessage(message, channel)
    }
}
