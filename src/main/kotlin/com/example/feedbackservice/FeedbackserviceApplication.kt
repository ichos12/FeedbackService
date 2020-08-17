package com.example.feedbackservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Import(RabbitConfiguration::class)
@SpringBootApplication
class FeedbackserviceApplication

@Serializable
data class DataObject(
    var userName: String,
    var userEmail: String,
    var topic: String,
    var message: String
)

fun toObject(stringValue: String): DataObject {
	return Json.parse(DataObject.serializer(), stringValue)
}

fun toJson(obj: DataObject): String {
	return Json.stringify(DataObject.serializer(), obj)
}

fun main(args: Array<String>) {
	runApplication<FeedbackserviceApplication>(*args)
}

