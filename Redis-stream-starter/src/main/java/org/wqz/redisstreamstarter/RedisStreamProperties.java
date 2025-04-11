package org.wqz.redisstreamstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis.stream")
public class RedisStreamProperties {

    private String streamKey;
    private String group;
    private String consumer;

    public String getStreamKey() {
        return streamKey;
    }

    public void setStreamKey(String streamKey) {
        this.streamKey = streamKey;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }
}    