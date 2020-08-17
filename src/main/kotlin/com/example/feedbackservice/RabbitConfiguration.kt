package com.example.feedbackservice

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfiguration {

    @Bean
    fun connectionFactory(): ConnectionFactory {
        return CachingConnectionFactory("localhost")
    }

    @Bean
    fun amqpAdmin(): AmqpAdmin {
        return RabbitAdmin(connectionFactory())
    }

    @Bean
    fun rabbitTemplate(): RabbitTemplate {
        return RabbitTemplate(connectionFactory())
    }

    @Bean
    fun myQueue1(): Queue {
        return Queue("queue1")
    }

    @Bean
    fun myQueue2(): Queue {
        return Queue("queue2")
    }

    @Bean
    fun fanoutExchangeA(): FanoutExchange {
        return FanoutExchange("exchange")
    }

    @Bean
    fun binding1(): Binding {
        return BindingBuilder.bind(myQueue1()).to(fanoutExchangeA())
    }

    @Bean
    fun binding2(): Binding {
        return BindingBuilder.bind(myQueue2()).to(fanoutExchangeA())
    }
}