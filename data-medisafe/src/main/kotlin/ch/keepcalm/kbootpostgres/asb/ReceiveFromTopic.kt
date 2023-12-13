package ch.keepcalm.kbootpostgres.asb

import com.azure.core.util.IterableStream
import com.azure.messaging.servicebus.ServiceBusClientBuilder
import com.azure.messaging.servicebus.ServiceBusReceivedMessage
import com.azure.messaging.servicebus.ServiceBusReceiverClient
import com.azure.messaging.servicebus.models.ServiceBusReceiveMode.RECEIVE_AND_DELETE

fun main() {
    val connectionString =
        "Endpoint=sb://kboot-servicebus.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=O+JPZ+W/RHW27U271K8iQmnJnarNgypFR+ASbE339vQ="
    val queueName = "kboot-queue"

    // Create a ServiceBusReceiverClient for the queue
    val receiverClient: ServiceBusReceiverClient = ServiceBusClientBuilder()
        .connectionString(connectionString)
        .receiver()
        .queueName(queueName)
        .receiveMode(RECEIVE_AND_DELETE)
        .buildClient()

    // Receive messages from the queue
    val messages: IterableStream<ServiceBusReceivedMessage> = receiverClient.receiveMessages(10)

    // Process the messages
    for (message in messages) {
        println("Received message: ${message.body}")
    }

    // Be sure to close the receiver when you're done
    receiverClient.close()
}
