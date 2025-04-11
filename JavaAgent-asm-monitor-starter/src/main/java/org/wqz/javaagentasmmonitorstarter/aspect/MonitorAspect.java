package org.wqz.javaagentasmmonitorstarter.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.wqz.javaagentasmmonitorstarter.kafka.KafkaProducerService;
import org.wqz.javaagentasmmonitorstarter.properties.MonitorProperties;

@Aspect
@Component
public class MonitorAspect {
    private static final Logger logger = LoggerFactory.getLogger(MonitorAspect.class);
    private final MonitorProperties monitorProperties;
    private final KafkaProducerService kafkaProducerService;

    public MonitorAspect(MonitorProperties monitorProperties, KafkaProducerService kafkaProducerService) {
        this.monitorProperties = monitorProperties;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Around("execution(* " + "${monitor.packageToMonitor}..*.*(..))")
    public Object monitorMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        String methodName = joinPoint.getSignature().toShortString();
        String message = "Method " + methodName + " executed in " + executionTime + " ms";
        logger.info(message);
        kafkaProducerService.sendMessage(monitorProperties.getKafkaTopic(), message);
        return result;
    }
}    