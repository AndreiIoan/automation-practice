package com.automationpractice.ui.pageObjects;

import com.automationpractice.configuration.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class CategoryPage extends BasePage {

    private SelenideElement addToCartButton = $("a[title='Add to cart']");
    private SelenideElement quickViewButton = $("a.quick-view");
    private SelenideElement continueShoppingButton = $("span[title='Continue shopping']");
    private List<SelenideElement> homepageProducts = $$("img[itemprop='image']").filter(Condition.visible);

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

    public void continueShopping() {
        log.info("Pressing continue shopping button.");
        clickElement(continueShoppingButton);
    }

    public boolean itemsArePresentInCategory() {
        return $$("img[itemprop='image']").filter(Condition.visible).size() != 0;
    }

    private void hoverFirstProduct() {
        log.info("Hovering over the first product.");
        homepageProducts.get(0).hover();
    }
}
