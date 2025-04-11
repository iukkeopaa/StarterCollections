package org.wqz.bytebuddymonitorstarter;

import jakarta.annotation.PostConstruct;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

@Configuration
@EnableConfigurationProperties(MonitorProperties.class)
@ConditionalOnProperty(prefix = "monitor", name = "enabled", havingValue = "true")
public class MonitorAutoConfiguration {

    @Autowired
    private MonitorProperties monitorProperties;

    @Autowired
    private Instrumentation instrumentation;

    @PostConstruct
    public void init() {
        new AgentBuilder.Default()
              .type(ElementMatchers.nameStartsWith(monitorProperties.getTargetPackages()))
              .transform(new AgentBuilder.Transformer() {
                  @Override
                  public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                      return null;
                  }


//                  public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
//                      return builder.method(ElementMatchers.any())
//                              .intercept(MethodDelegation.to(MonitorInterceptor.class));
//                  }



                })
              .installOn(instrumentation);
    }
}    