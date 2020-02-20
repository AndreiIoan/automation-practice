package com.automationpractice.configuration;

import com.codeborne.selenide.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
@ConfigurationProperties(prefix = "tests")
public class SelenideConfiguration {

    @Value("${tests.baseUrl}")
    private String baseUrl;

    @PostConstruct
    public void init() {
        Configuration.baseUrl = baseUrl;
        Configuration.browser = "com.automationpractice.configuration.ChromeWebDriverProvider";
        Configuration.timeout = TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS);
        Configuration.dismissModalDialogs = true;
        Configuration.driverManagerEnabled = false;
    }
}
