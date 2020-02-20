package com.automationpractice.configuration;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BasePage {

    protected void addToCart() {

    }
    protected void quickViewProduct() {

    }

    protected SelenideElement sendTextToField(SelenideElement selenideElement, String text) {
        return selenideElement.setValue(text);
    }

    protected void clickElement(SelenideElement selenideElement) {
        selenideElement.click();
    }
}
