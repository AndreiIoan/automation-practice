package com.automationpractice.ui.pageObjects;

import com.automationpractice.configuration.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Component
public class Homepage extends BasePage {

    private SelenideElement addToCartButton = $("a[title='Add to cart']");
    private SelenideElement searchField = $("#search_query_top");
    private SelenideElement quickViewButton = $("a.quick-view");
    private List<SelenideElement> homepageProductsContainer = $$(".product-image-container");

    public void navigateToPage(String pageName) {
        clickElement($$("a").filter(Condition.text(pageName)).first());
    }

    public void search(String text) {
        sendTextToField(searchField, text).pressEnter();
    }

    @Override
    public void addToCart() {
        hoverFirstProduct();
        clickElement(addToCartButton);
    }

    @Override
    public void quickViewProduct() {
        hoverFirstProduct();
        clickElement(quickViewButton);
    }

    private void hoverFirstProduct() {
        homepageProductsContainer.get(0).$("img").hover();
    }
}
