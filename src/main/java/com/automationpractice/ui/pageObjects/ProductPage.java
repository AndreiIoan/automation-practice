package com.automationpractice.ui.pageObjects;

import com.codeborne.selenide.Condition;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Component
public class ProductPage {

    public void selectSize(String size) {
        $("#group_1").selectOption(size);
    }

    public void selectColor(String color) {
        $("#color_to_pick_list").$("a[name='" + color + "']").click();
    }

    public void addToCart() {
        $$("span").filter(Condition.text("Add to cart")).first().click();
    }

    public void addToWishlist() {
        $("#wishlist_button").click();
    }

    public void increaseQuantity() {

    }

    public void decreaseQuantity() {

    }

    public void viewLargerImage() {
        $$("span").filter(Condition.text("View larger")).first().click();
    }

    public void closeEnlargedImage() {
        $("a[title='Close']").click();
    }
}
