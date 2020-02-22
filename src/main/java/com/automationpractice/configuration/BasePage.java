package com.automationpractice.configuration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;


public abstract class BasePage {

    public static String generatedEmailAddress;

    public static void switchToFrame(String frameId) {
        switchTo().frame(frameId);
    }

    protected static void switchToFrame(WebElement webElement) {
        switchTo().frame(webElement);
    }

    protected SelenideElement sendTextToField(SelenideElement selenideElement, String text) {
        return selenideElement.waitUntil(Condition.exist, Configuration.timeout).setValue(text);
    }

    protected void selectValueFromDropdown(SelenideElement selenideElement, String value) {
        selenideElement.waitUntil(Condition.exist, Configuration.timeout).selectOption(value);
    }

    protected void clickElement(SelenideElement selenideElement) {
        selenideElement.waitUntil(Condition.exist, Configuration.timeout).click();
    }

    public static boolean errorNotPresent() {
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
