package com.automationpractice.ui.pageObjects;

import com.automationpractice.configuration.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

@Slf4j
@Component
public class AuthenticationPage extends BasePage {

    private SelenideElement createAccountField = $("#email_create");
    private SelenideElement emailField = $("#email");
    private SelenideElement passwordField = $("#passwd");
    private SelenideElement authenticationErrorMessage = $$("li").filter(Condition.text("Authentication failed")).first();
    private SelenideElement titleRadioButton = $$("input[name='id_gender']").first();
    private SelenideElement customerFirstNameField = $("#customer_firstname");
    private SelenideElement customerLastNameField = $("#customer_lastname");
    private SelenideElement emailAddressField = $("#email");
    private SelenideElement dateOfBirthDaysField = $("#days");
    private SelenideElement dateOfBirthMonthsField = $("#months");
    private SelenideElement dateOfBirthYearsField = $("#years");
    private SelenideElement addressFirstNameField = $("#firstname");
    private SelenideElement addressLastNameField = $("#lastname");
    private SelenideElement addressCompanyField = $("#company");
    private SelenideElement addressLine1Field = $("#address1");
    private SelenideElement addressLine2Field = $("#address2");
    private SelenideElement addressCityField = $("#city");
    private SelenideElement addressStateDropdown = $("#id_state");
    private SelenideElement addressPostalCodeField = $("#postcode");
    private SelenideElement addressCountryDropdown = $("#id_country");
    private SelenideElement addressAdditionalInfoField = $("#other");
    private SelenideElement addressHomePhoneField = $("#phone");
    private SelenideElement addressMobilePhoneField = $("#phone_mobile");
    private SelenideElement addressAliasField = $("#alias");
    private SelenideElement signUpForNewsletterButton = $("#newsletter");
    private SelenideElement receiveSpecialOffersButton = $("#optin");
    private SelenideElement registerButton = $$("span").filter(Condition.text("Register")).first();




    public void gotoCreateAccount(String emailAddress) {
        log.info("Attempting to advance to create account using {} email address", emailAddress);
        sendTextToField(createAccountField, emailAddress).pressEnter();
    }

    public void createDefaultAccountWithMandatoryFields() {
        log.info("Creating account by filling the mandatory fields only.");
        sendTextToField(customerFirstNameField, "Andrei");
        sendTextToField(customerLastNameField, "Test");
        if(StringUtils.isEmpty(emailAddressField.getText()))
            sendTextToField(emailAddressField, BasePage.generatedEmailAddress);
        sendTextToField(passwordField, "password");
        sendTextToField(addressFirstNameField, "Andrei");
        sendTextToField(addressLastNameField, "Test");
        sendTextToField(addressLine1Field, "First line address");
        sendTextToField(addressCityField, "Bucharest");
        selectValueFromDropdown(addressStateDropdown, "Washington");
        sendTextToField(addressPostalCodeField, "12345");
        if(!addressCountryDropdown.getSelectedOption().getText().equals("United States"))
            selectValueFromDropdown(addressCountryDropdown, "Andrei");
        sendTextToField(addressMobilePhoneField, "1234567890");
        sendTextToField(addressAliasField, "Work");
        registerAccount();
        assertTrue(errorNotPresent());
    }

    private void registerAccount() {
        clickElement(registerButton);
    }

    public void login(String emailAddress, String password) {
        log.info("Attempting to log in using {} email and {} password.", emailAddress, password);
        sendTextToField(emailField, emailAddress);
        sendTextToField(passwordField, password).pressEnter();
    }

    public void login() {
        log.info("Logging in with default credentials.");
        sendTextToField(emailField, "");
        sendTextToField(passwordField, "password");
    }

    public boolean authenticationFailed() {
        return authenticationErrorMessage.waitUntil(Condition.visible, Configuration.timeout).isDisplayed();
    }
}
