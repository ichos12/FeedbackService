package com.example.feedbackservice

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired

@Controller
class HtmlController {
    @Autowired
    var template: AmqpTemplate? = null

    @GetMapping("/index", "/")
    fun page(model: Model): String {
        model["title"] = "Feedback service"
        model["data"] = DataObject("","","","")
        return "index"
    }

    @PostMapping("/index")
    fun pageSubmit(model: Model, @ModelAttribute data: DataObject): String {
        model["data"] = data
        template?.convertAndSend("queue", toJson(data))
        return "success"
    }
}