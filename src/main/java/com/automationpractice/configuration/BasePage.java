package com.automationpractice.configuration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$$;

@Slf4j
public abstract class BasePage {

    public static String generatedEmailAddress;

    protected SelenideElement sendTextToField(SelenideElement selenideElement, String text) {
        return selenideElement.waitUntil(Condition.exist, Configuration.timeout).setValue(text);
    }

    protected void selectValueFromDropdown(SelenideElement selenideElement, String value) {
        selenideElement.waitUntil(Condition.exist, Configuration.timeout).selectOption(value);
    }

    protected void clickElement(SelenideElement selenideElement) {
        selenideElement.waitUntil(Condition.exist, Configuration.timeout).click();
    }

    protected boolean errorNotPresent() {
        return $$("div.alert").size() == 0;
    }

    public static String generateRandomEmail() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString() + "@yahoo.com";
    }
}
