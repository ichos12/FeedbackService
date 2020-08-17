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

    @RabbitListener(queues = ["queue1"])
    fun processQueue1(json: String) {
        logger.info("start queue1")
        val data = toObject(json)
        val sender = Sender("gusev.maxim.val@gmail.com","1stalker2")

        sender.send(data.topic, data.message + "\nfrom " + data.userName, data.userEmail, "ichos52@gmail.com")
        logger.info("Received from queue1: ${data.userName} ${data.userEmail} ${data.topic} ${data.message}")
    }

    @RabbitListener(queues = ["queue2"])
    fun processQueue2(json: String) {
        logger.info("start queue2")
        val data = toObject(json)
        val sender = Sender("gusev.maxim.val@gmail.com","1stalker2")

        sender.send(data.topic, data.message + "\nfrom " + data.userName, data.userEmail, "magus78@yandex.ru")
        logger.info("Received from queue2: ${data.userName} ${data.userEmail} ${data.topic} ${data.message}")
    }
}