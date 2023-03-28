package za.co.digitalcowboy.logistics.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import za.co.digitalcowboy.logistics.service.domain.Order;

import java.util.function.Consumer;

@SpringBootApplication
@Slf4j
public class LogisticsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsServiceApplication.class, args);
    }

//    @Bean
//    public Consumer<Order> orderConsumer() {
//        return incomingOrder -> log.info("Incoming Number : {}", incomingOrder);
//    }


}
