package com.automationpractice.ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
@Component
public class SearchPage {

    private SelenideElement searchPageName = $("span.navigation_page");
    private List<SelenideElement> searchProducts = $$("img[itemprop='image']").filter(Condition.visible);

    public String searchPageTitle() {
        return searchPageName.text();
    }

    public String searchItem(String item) {
        return searchProducts.get(0).attr("name");
    }
}
