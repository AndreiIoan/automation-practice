package com.automationpractice.ui.pageObjects;

import com.automationpractice.configuration.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class Homepage extends BasePage {

    private SelenideElement addToCartButton = $("a[title='Add to cart']");
    private SelenideElement searchField = $("#search_query_top");
    private SelenideElement quickViewButton = $("a.quick-view");
    private SelenideElement cartContainerPopup = $("#layer_cart");
    private List<SelenideElement> homepageProducts = $$("img[itemprop='image']").filter(Condition.visible);

    public void navigateToPage(String pageName) {
        log.info("Accessing {} page", pageName);
        clickElement($$("a").filter(Condition.text(pageName)).first().waitUntil(Condition.appears, Configuration.timeout));
    }

    public void gotoProductPage() {
        log.info("Navigating to product page.");
        hoverFirstProduct();
        clickElement(homepageProducts.get(0));
    }

    public void search(String text) {
        log.info("Searching for the following item {}", text);
        sendTextToField(searchField, text).pressEnter();
    }

    public void addProductToCart() {
        log.info("Adding product to cart.");
        hoverFirstProduct();
        clickElement(addToCartButton);
    }

    public void quickViewProduct() {
        log.info("Opening quick view of product.");
        hoverFirstProduct();
        clickElement(quickViewButton);
    }

    public void proceedToCheckout() {
        waitForCheckoutPopupToBePresent();
        log.info("Continuing to checkout.");
        clickElement(cartContainerPopup.$$("span").filter(Condition.text("Proceed to checkout")).first());
    }

    private void waitForCheckoutPopupToBePresent() {
        log.info("Waiting for proceed to checkout popup to be displayed.");
        $("#layer_cart").waitUntil(Condition.appears, Configuration.timeout).isDisplayed();
    }

    private void hoverRandomProduct() {
        log.info("Hovering a random product.");
        Random random = new Random();
        homepageProducts.get(random.nextInt(homepageProducts.size()-1)).hover();
    }

    private void hoverFirstProduct() {
        log.info("Hovering over the first product.");
        homepageProducts.get(0).hover();
    }

    public void logout() {
        log.info("Logging out.");
        clickElement($$("a").filter(Condition.text("Sign out")).first().waitUntil(Condition.appears, Configuration.timeout));
    }

    public boolean userIsAuthenticated() {
        return $$("a").filter(Condition.text("Sign out")).size() != 0;
    }

    public boolean cartIsEmpty() {
        return $$("span[class='ajax_cart_no_product']").size() != 0;
    }
}
