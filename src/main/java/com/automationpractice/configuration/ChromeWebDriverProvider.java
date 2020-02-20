package com.automationpractice.configuration;

import com.codeborne.selenide.WebDriverProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@Slf4j
public class ChromeWebDriverProvider implements WebDriverProvider {

    private ResourceLoader resourceLoader = new GenericApplicationContext();
    private String CHROMEDRIVER_PATH;

    {
        try {
            CHROMEDRIVER_PATH = resourceLoader.getResource("classpath:chromedriver.exe").getFile().getAbsolutePath();
        } catch (IOException e) {
            log.error("Chromedriver not found at classpath.", e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        ChromeOptions options = createChromeOptions();
        return new ChromeDriver(options);
    }

    private ChromeOptions createChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        return chromeOptions;
    }
}
