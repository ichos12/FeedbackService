package com.example.feedbackservice

import org.apache.logging.log4j.LogManager
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Component

@Import(RabbitConfiguration::class)
@EnableRabbit
@Component
class RabbitMqListener {
    internal var logger = LogManager.getLogger(RabbitMqListener::class)
    val sender = Sender("gusev.maxim.val@gmail.com","1stalker2")

    @RabbitListener(queues = ["queue"])
    fun processQueue(json: String) {
        val data = toObject(json)

        sender.send(data.topic, data.message + "\nfrom " + data.userName, "gusev.maxim.val@gmail.com", data.userEmail)
        logger.info("Received from queue: ${data.userName} ${data.userEmail} ${data.topic} ${data.message}")
    }
}