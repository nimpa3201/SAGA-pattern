package com.market.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderApplicationQueueConfig {

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Value("${message.exchange}")
    private String exchange;

    @Value("${message.queue.product}")
    private String queueProduct;

    @Value("${message.queue.payment}")
    private String queuePayment;


    @Value("${message.err.exchange}")
    private String exchangeErr;

    @Value("${message.queue.err.order}")
    private String queueErrOrder;

    @Value("${message.queue.err.product}")
    private String queueErrProduct;

    @Bean public TopicExchange exchange(){ return new TopicExchange(exchange);} //마켓이란 이름의 exchange 생성
    @Bean public Queue queueProduct(){ return new Queue(queueProduct);} //market.product 큐 생성
    @Bean public Queue queuePayment(){return new Queue(queuePayment);} // market.payment 큐 생성

    //바인딩 생성 -> 큐 이름과 똑같이 만듦
    @Bean public Binding bindingProduct(){return BindingBuilder.bind(queueProduct()).to(exchange()).with(queueProduct);}
    @Bean public Binding bindingPayment(){return BindingBuilder.bind(queuePayment()).to(exchange()).with(queuePayment);}


    @Bean public  TopicExchange exchangeErr(){return new TopicExchange(exchangeErr);}

    @Bean public Queue queueErrorProduct() { return new Queue(queueErrProduct);} // market.ErrorProduct 생성
    @Bean public Queue queueErrorOrder() {return new Queue(queueErrOrder);} //market.ErrorOrder

    @Bean public Binding bindErrorProduct(){ return BindingBuilder.bind(queueErrorProduct()).to(exchangeErr()).with(queueErrProduct);}
    @Bean public Binding bindErrorOrder(){ return BindingBuilder.bind(queueErrorOrder()).to(exchangeErr()).with(queueErrOrder);}






}
