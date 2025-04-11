package org.wqz.javaagentasmmonitorstarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "monitor")
public class MonitorProperties {
    private String packageToMonitor = "com.example";
    private String kafkaTopic = "monitor-topic";

    public String getPackageToMonitor() {
        return packageToMonitor;
    }

    public void setPackageToMonitor(String packageToMonitor) {
        this.packageToMonitor = packageToMonitor;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }
}