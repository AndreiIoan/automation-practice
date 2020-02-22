package com.automationpractice.ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Component
public class MyAccountPage {

    private SelenideElement myAccountText = $("h1.page-heading");

    public boolean amOnAccountPage() {
        return myAccountText.waitUntil(Condition.appears, Configuration.timeout).isDisplayed();
    }
}
