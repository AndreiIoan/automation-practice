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
public class CheckoutPage extends BasePage {

    private SelenideElement termsOfServiceCheckboxButton = $("#cgv");
    private SelenideElement proceedToCheckoutButton = $$("span").filter(Condition.text("Proceed to checkout")).first();
    private SelenideElement payByBankwireButton = $(".bankwire");
    private SelenideElement confirmOrderButton = $$("span").filter(Condition.text("I confirm my order")).first();

    public void checkTermsOfServiceCheckbox() {
        log.info("Agreeing to terms of service.");
        clickElement(termsOfServiceCheckboxButton);
    }

    public void continueToCheckout() {
        log.info("Continuing the checkout process.");
        clickElement(proceedToCheckoutButton);
    }

    public void selectPayByBankwire() {
        log.info("Selecting to pay by bank wire");
        clickElement(payByBankwireButton);
    }

    public void confirmOrder() {
        log.info("Confirming order.");
        clickElement(confirmOrderButton);
    }

    public boolean orderWasSuccessfullyPlaced() {
        return $$("strong").filter(Condition.text("Your order on My Store is complete.")).size() > 0;
    }
}
