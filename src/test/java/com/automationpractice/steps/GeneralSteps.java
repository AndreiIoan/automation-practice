package com.automationpractice.steps;

import com.automationpractice.configuration.CucumberStepDefinition;
import com.automationpractice.ui.pageObjects.AuthenticationPage;
import com.automationpractice.ui.pageObjects.Homepage;

import cucumber.api.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;

@Slf4j
@CucumberStepDefinition
public class GeneralSteps {

    @Autowired
    private Homepage homepage;
    @Autowired
    private AuthenticationPage authenticationPage;

    @Given("The web app is running")
    public void launchWebApp() {
        open("/");
    }

    @And("I navigate to the {string} page")
    public void iNavigateToPage(String page) {
        homepage.navigateToPage(page);
    }

    @When("I try to log in with invalid {string} and {string}")
    public void logInWithInvalidCredentials(String wrongEmail, String wrongPassword) {
        authenticationPage.login(wrongEmail, wrongPassword);
    }

    @Then("I should receive an error message")
    public void iShouldReceiveAnErrorMessage() {
        assertTrue(authenticationPage.authenticationFailed());
        log.info("Failed to authenticate user.");
    }

}
