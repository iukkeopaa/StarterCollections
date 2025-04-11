package org.wqz.bytebuddymonitorstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "monitor")
public class MonitorProperties {
    private boolean enabled = true;
    private String[] targetPackages;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTargetPackages() {
        return targetPackages;
    }

    public void setTargetPackages(String[] targetPackages) {
        this.targetPackages = targetPackages;
    }
}    