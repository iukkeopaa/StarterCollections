package org.wqz.loginjectstarter;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.instrument.Instrumentation;

@Configuration
@EnableConfigurationProperties(LogInjectionProperties.class)
@ConditionalOnProperty(prefix = "log.injection", name = "enabled", havingValue = "true")
public class LogInjectionAutoConfiguration {

    @Autowired
    private LogInjectionProperties properties;

    @Bean
    public AgentBuilder agentBuilder(Instrumentation instrumentation) {
        return new AgentBuilder.Default()
               .type(ElementMatchers.any())
               .transform((builder, typeDescription, classLoader, module) ->
                        builder.method(ElementMatchers.isPublic())
                               .intercept(MethodDelegation.to(LogInjectionInterceptor.class)))
               .installOn(instrumentation);
    }
}
    