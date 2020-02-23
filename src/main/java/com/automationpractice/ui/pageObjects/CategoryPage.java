package com.automationpractice.ui.pageObjects;

import com.automationpractice.configuration.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class CategoryPage extends BasePage {

    private SelenideElement addToCartButton = $("a[title='Add to cart']");
    private SelenideElement cartButton = $("a[title='View my shopping cart']");
    private SelenideElement quickViewButton = $("a.quick-view");
    private SelenideElement continueShoppingButton = $("span[title='Continue shopping']");
    private List<SelenideElement> products = $$("img[itemprop='image']").filter(Condition.visible);
    private List<SelenideElement> wishlistError = $$("p.fancybox-error");
    private SelenideElement addToWishlistButton = $("a.addToWishlist");
    private SelenideElement wishlistSuccess = $("p.fancybox-error");
    private SelenideElement closeErrorMessage = $("a[title='Close']");

    public void gotoCartPage() {
        log.info("Opening the shopping cart.");
        clickElement(cartButton);
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

    public void continueShopping() {
        log.info("Pressing continue shopping button.");
        clickElement(continueShoppingButton);
    }

    public boolean itemsArePresentInCategory() {
        return $$("img[itemprop='image']").filter(Condition.visible).size() != 0;
    }

    public void addProductToWishList() {
        products.get(0).hover();
        clickElement(addToWishlistButton);
    }

    public boolean addToWishlistFailed() {
        return wishlistError.size() != 0;
    }

    public boolean addToWishlistSucessful() {
        return wishlistSuccess.getText().equals("Added to your wishlist.");
    }

    public void closeErrorMessage() {
        clickElement(closeErrorMessage);
    }

    private void hoverFirstProduct() {
        log.info("Hovering over the first product.");
        products.get(0).hover();
    }
}
