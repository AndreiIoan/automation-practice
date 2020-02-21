package com.automationpractice.ui.pageObjects;

import com.automationpractice.configuration.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class AuthenticationPage extends BasePage {

    private SelenideElement createAccountField = $("#email_create");
    private SelenideElement emailField = $("#email");
    private SelenideElement passwordField = $("#passwd");
    private SelenideElement authenticationErrorMessage = $$("li").filter(Condition.text("Authentication failed")).first();


    public void createAccount(String emailAddress) {
        log.info("Attempting to create account using {} email address", emailAddress);
        sendTextToField(createAccountField, emailAddress).pressEnter();
    }

    public void login(String emailAddress, String password) {
        log.info("Attempting to log in using {} email and {} password.", emailAddress, password);
        sendTextToField(emailField, emailAddress);
        sendTextToField(passwordField, password).pressEnter();
    }

    public void login() {
        log.info("Logging in with default credentials.");
        sendTextToField(emailField, "");
        sendTextToField(passwordField, "password");
    }

    public boolean authenticationFailed() {
        return authenticationErrorMessage.waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    }

    private boolean errorPresent() {
        return $$("").size() != 0;
    }
}
