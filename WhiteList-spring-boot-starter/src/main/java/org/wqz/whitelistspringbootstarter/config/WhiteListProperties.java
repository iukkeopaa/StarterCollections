package org.wqz.whitelistspringbootstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 属性类
 * @Author: wjh
 * @Date: 2025/4/11 上午8:24
 */

@ConfigurationProperties("bugstack.whitelist")
public class WhiteListProperties {
    private String users;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
