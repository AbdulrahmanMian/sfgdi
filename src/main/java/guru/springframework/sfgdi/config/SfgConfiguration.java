package guru.springframework.sfgdi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("guru")
// The line above looks for properties which start with guru and binds them.
// As long as these names(username,password etc.) match, it will get bound properly.
@Configuration
public class SfgConfiguration {
    private String username;
    private String password;
    private String jdbcurl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcurl() {
        return jdbcurl;
    }

    public void setJdbcurl(String jdbcurl) {
        this.jdbcurl = jdbcurl;
    }
}
