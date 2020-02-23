package com.automationpractice.ui.pageObjects;

import com.automationpractice.configuration.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class MyAccountPage extends BasePage {

    private SelenideElement myAccountText = $("h1.page-heading");
    private ElementsCollection myAccountLinks = $$("span");
    private ElementsCollection wishlistRows = $$("tr");

    public boolean amOnAccountPage() {
        return myAccountText.waitUntil(Condition.appears, Configuration.timeout).isDisplayed();
    }

    public void gotoAccountLink(String linkName) {
        clickElement(myAccountLinks.filter(Condition.text(linkName)).first());
    }

    public boolean wishlistPresent() {
        return $$("a").filter(Condition.text("My wishlist")).size() != 0;
    }
}
