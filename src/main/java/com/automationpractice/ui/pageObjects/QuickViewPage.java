package com.automationpractice.ui.pageObjects;

import com.automationpractice.configuration.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class QuickViewPage extends BasePage {

    private WebElement quickViewFrame = $("iframe.fancybox-iframe");
    private List<SelenideElement> sizeOptions = sizeDropdownContainer().$$("option");
    private SelenideElement addToCartButton = $$("span").filter(Condition.text("Add to cart")).first();
    private SelenideElement addToWishlistButton = $("#wishlist_button");
    private SelenideElement increaseQtyButton = $("a.product_quantity_up");
    private SelenideElement decreaseQtyButton = $("a.product_quantity_down");
    private SelenideElement increaseImageSizeButton = $$("span").filter(Condition.text("View larger")).first();
    private SelenideElement closeZoomedImageButton = $("a[title='Close']");
    private SelenideElement colorContainer = $("#color_to_pick_list");

    public void switchToQuickViewFrame() {
        log.info("Switching to the quick view frame.");
        switchToFrame(quickViewFrame);
    }

    public void selectSize(String size) {
        clickElement(sizeDropdownContainer());
        for (SelenideElement selenideElement : sizeOptions) {
            if (selenideElement.text().equals(size)) {
                clickElement(selenideElement);
                break;
            }
        }
        log.info("Selecting {} size.", size);
    }

    public void selectRandomNotSelectedColor() {
        List<SelenideElement> colors = colorContainer.$$("a");
        for (SelenideElement color : colors) {
            if (!color.parent().attr("class").equals("selected")) {
                clickElement(color);
                log.info("Selecting {} color.", color.attr("name"));
                break;
            }
        }
    }

    public void addToCart() {
        log.info("Adding product to cart.");
        clickElement(addToCartButton);
    }

    public void addToWishlist() {
        log.info("Adding product to wish list.");
        clickElement(addToWishlistButton);
    }

    public void increaseQuantity() {
        log.info("Increasing quantity of the product.");
        clickElement(increaseQtyButton);
    }

    public void decreaseQuantity() {
        log.info("Decreasing quantity of the product.");
        clickElement(decreaseQtyButton);
    }

    public void viewLargerImage() {
        log.info("Zooming in the image of the product.");
        clickElement(increaseImageSizeButton);
    }

    public void closeEnlargedImage() {
        log.info("Closing zoomed in image.");
        clickElement(closeZoomedImageButton);
    }

    private SelenideElement sizeDropdownContainer() {
        return $("#uniform-group_1");
    }
}
