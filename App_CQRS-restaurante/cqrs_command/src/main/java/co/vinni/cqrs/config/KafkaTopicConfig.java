package co.vinni.cqrs.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("order-events", 1, (short) 1);
    }

    @Bean
    public NewTopic promotionTopic() {
        return new NewTopic("promotion-events", 1, (short) 1);
    }
}
