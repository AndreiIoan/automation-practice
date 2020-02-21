package com.automationpractice.ui.pageObjects;

import com.automationpractice.configuration.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class CartPage extends BasePage {

    private SelenideElement proceedToCheckoutButton = $$("span").filter(Condition.text("Proceed to checkout")).first();

    public void continueToCheckout() {
        log.info("Continuing the checkout process.");
        clickElement(proceedToCheckoutButton);
    }

}
