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

package ch.keepcalm.medisafe.command.application

import ch.keepcalm.medisafe.command.domain.SafeCreatedEvent
import ch.keepcalm.medisafe.command.infrastructure.logger
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.EventMessage
import org.springframework.stereotype.Component
//
//@Component
//@ProcessingGroup(value = "amqpEvents")
//class SafeEventHandler {
//
//    /**
//     * Safe created event.
//     */
//    @EventHandler
//    fun <T : EventMessage<Any>> on(event: SafeCreatedEvent) {
//        logger.info { "::--> SafeEventHandler  Handling event: ${event.safeId}"}
//    }
//
//}
