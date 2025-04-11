package org.wqz.javaagentasmmonitorstarter.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.wqz.javaagentasmmonitorstarter.aspect.MonitorAspect;
import org.wqz.javaagentasmmonitorstarter.kafka.KafkaConsumerService;
import org.wqz.javaagentasmmonitorstarter.kafka.KafkaProducerService;
import org.wqz.javaagentasmmonitorstarter.properties.MonitorProperties;

@Configuration
@EnableConfigurationProperties(MonitorProperties.class)
public class MonitorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MonitorAspect monitorAspect(MonitorProperties monitorProperties, KafkaProducerService kafkaProducerService) {
        return new MonitorAspect(monitorProperties, kafkaProducerService);
    }

    @Bean
    public KafkaProducerService kafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaProducerService(kafkaTemplate);
    }

    @Bean
    public KafkaConsumerService kafkaConsumerService() {
        return new KafkaConsumerService();
    }
}    