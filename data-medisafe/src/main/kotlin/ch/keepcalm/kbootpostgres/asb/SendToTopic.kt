package ch.keepcalm.kbootpostgres.asb

import com.azure.messaging.servicebus.ServiceBusClientBuilder
import com.azure.messaging.servicebus.ServiceBusMessage
import com.azure.messaging.servicebus.ServiceBusSenderClient

fun main() {
    val connectionString = "Endpoint=sb://kboot-servicebus.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=O+JPZ+W/RHW27U271K8iQmnJnarNgypFR+ASbE339vQ="
    val topicName = "kboot-topic"

    // Create a ServiceBusSenderClient for the topic
    val senderClient: ServiceBusSenderClient = ServiceBusClientBuilder()
        .connectionString(connectionString)
        .sender()
        .topicName(topicName)
        .buildClient()

    // Create a message and send it
    val message = ServiceBusMessage("Hello, World!")
    senderClient.sendMessage(message)

    // Be sure to close the sender when you're done
    senderClient.close()
}
