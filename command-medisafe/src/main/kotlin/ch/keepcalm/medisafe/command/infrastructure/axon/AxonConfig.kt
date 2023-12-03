/*
 * Copyright (c) 2010-2021. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.keepcalm.medisafe.command.infrastructure.axon

import com.rabbitmq.client.Channel
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig {

    @Bean
    fun eventsExchange(): TopicExchange {
        return TopicExchange(TOPIC_EXCHANGE_NAME)
    }

    @Bean
    fun eventsQueue(): Queue {
        return Queue(QUEUE_NAME, true)
    }

    @Bean
    fun eventsBinding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue)
            .to(exchange)
            .with("#")
    }

    companion object {
        const val TOPIC_EXCHANGE_NAME = "SafeCreatedEvent"
        const val QUEUE_NAME = "SafeCreatedEvent"
    }

    /**
     * The SpringAMQPMessageSource allows event processors to read messages from a queue instead of the event store or
     * event bus. It acts as an adapter between Spring AMQP and the SubscribableMessageSource needed by these processors.
     *
     * @param messageConverter Converter to/from AMQP Messages to/from Axon Messages.
     */
    @Bean
    fun amqpMessageSource(messageConverter: AMQPMessageConverter): SpringAMQPMessageSource {
        return object : SpringAMQPMessageSource(messageConverter) {
            @RabbitListener(queues = [QUEUE_NAME])
            override fun onMessage(message: Message?, channel: Channel?) {
                println("amqp event $message received")
                super.onMessage(message, channel)
            }
        }
    }


}
