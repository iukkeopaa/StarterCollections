package org.wqz.javaassistmonitoringstarter;

import javassist.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class MonitorPostProcessor implements BeanPostProcessor {

    private final MethodMonitor methodMonitor;

    public MonitorPostProcessor(MethodMonitor methodMonitor) {
        this.methodMonitor = methodMonitor;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        CtClass ctClass = null;
        try {
            ClassPool pool = ClassPool.getDefault();
            ctClass = pool.get(beanClass.getName());
            CtMethod[] methods = ctClass.getDeclaredMethods();
            for (CtMethod method : methods) {
                enhanceMethod(method, beanClass);
            }
            Class<?> enhancedClass = ctClass.toClass();
            return enhancedClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ctClass != null) {
                ctClass.detach();
            }
        }
        return bean;
    }

    private void enhanceMethod(CtMethod method, Class<?> beanClass) throws NotFoundException, CannotCompileException {
        CtClass ctClass = null;
        CtMethod copy = CtNewMethod.copy(method, ctClass, null);
        method.setName(method.getName() + "$old");
        ctClass.addMethod(copy);

        String methodName = method.getName();
        String className = beanClass.getName();
        String beforeCode = String.format("{ com.example.starter.monitor.MethodMonitor monitor = new com.example.starter.monitor.MethodMonitor();" +
                "java.lang.reflect.Method m = %s.class.getMethod(\"%s\"); monitor.beforeMethod(m); long startTime = System.currentTimeMillis(); }", className, methodName);
        String afterCode = "{ long endTime = System.currentTimeMillis(); long executionTime = endTime - startTime; " +
                "com.example.starter.monitor.MethodMonitor monitor = new com.example.starter.monitor.MethodMonitor();" +
                "java.lang.reflect.Method m = %s.class.getMethod(\"%s\"); monitor.afterMethod(m, executionTime); }";
        afterCode = String.format(afterCode, className, methodName);

        copy.insertBefore(beforeCode);
        copy.insertAfter(afterCode);
    }
}    