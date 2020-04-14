package com.bank.testsuite;

import com.bank.pages.*;
import com.bank.testbase.TestBase;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.bank.utility.Utility.getRandomString;
/*
 baseUrl :http://www.way2automation.com/angularjs-protractor/banking/#/login
 Project name: xyz-bank
 Group-Id : com.bank
        main package.
        create all packages and class that you created in previous project
        In Pages package
        1.HomePage
        2.CustomerLoginPage
        3.BankManagerLoginPage
        4.AddCustomerPage
        5.OpenAccountPage
        6.CustomersPage
        7.AccountPage - store Transaction, deposit and withdraw.

        test package.
        create all packages and class that you created in previous project

 1.BankTest
  Inside this create test methods
*/

public class BankTest extends TestBase {

    // Test data that is being used for the testing purpose

    String firstName = null;
    String lastName  = null;
    String customer  = firstName +" "+ lastName;
    String postcode  = "HA6 1NW";
    String currency  = "Pound";
    String depositAmount   = "100";
    String withdrawlAmount = "50";

    //Objects created for the various Pages for use in the tests
    HomePage homePage = new HomePage();
    BankManagerLoginPage bankManagerLoginPage = new BankManagerLoginPage();
    AddCustomerPage addCustomerPage = new AddCustomerPage();
    OpenAccountPage openAccountPage = new OpenAccountPage();
    CustomerLoginPage customerLoginPage = new CustomerLoginPage();
    CustomerPage customerPage = new CustomerPage();
    AccountPage accountPage = new AccountPage();
    CustomerDetailsPage customerDetailsPage = new CustomerDetailsPage();

    @BeforeTest (groups = {"regression"})
    public void setUp (){
         firstName = getRandomString (5);
         lastName  = getRandomString(4);
         customer  = firstName + " " + lastName;
    }

    /*
   1.bankManagerShouldAddCustomerSuccessfully.
    click On "Bank Manager Login" Tab
    click On "Add Customer" Tab
    enter FirstName
    enter LastName
    enter PostCode
    click On "Add Customer" Button
    popup display
    verify message "Customer added successfully"
    click on "ok" button on popup.
*/

    @Test(priority = 0, groups= {"regression"})
    public void bankManagerShouldAddCustomerSuccessfully() {
        homePage.clickOnBankManagerLoginTab();
        bankManagerLoginPage.clickOnAddCustomerTab();
        addCustomerPage.enterNewUserFirstName(firstName);
        addCustomerPage.enterNewUserLastName(lastName);
        addCustomerPage.enterNewUserPostcode(postcode);
        addCustomerPage.clickOnAddCustomerBtn();
        addCustomerPage.popUpDisplayMessage();
        Alert alert = driver.switchTo().alert();
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(alert.getText().contains("Customer added successfully"));
        alert.accept();
        bankManagerLoginPage.clickOnHomepageBtn();
    }

  /* 2. bankManagerShouldOpenAccountSuccessfully.
    click On "Bank Manager Login" Tab
    click On "Open Account" Tab
    Search customer that created in first test
    Select currency "Pound"
    click on "process" button
    popup displayed
    verify message "Account created successfully"
    click on "ok" button on popup.
*/

    @Test(priority = 1, groups= {"regression"})
    public void bankManagerShouldOpenAccountSuccessfully() {
        homePage.clickOnBankManagerLoginTab();
        bankManagerLoginPage.clickOnOpenAccountTab();
        openAccountPage.selectCustomerNameFromDropDown(customer);
        openAccountPage.selectCurrencyFromDropDown(currency);
        openAccountPage.clickOnProcessButton();
        openAccountPage.popUpDisplayMessage();
        Alert alert = driver.switchTo().alert();
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(alert.getText().contains("Account created successfully"));
        alert.accept();
        bankManagerLoginPage.clickOnHomepageBtn();
    }

    /*
      3. customerShouldLoginAndLogoutSuccessfully.
    click on "Customer Login" Tab
    search customer that you created.
    click on "Login" Button
    verify "Logout" Tab displayed.
    click on "Logout"
    verify "Your Name" text displayed.
*/
    @Test(priority = 2, groups= {"regression"})
    public void customerShouldLoginAndLogoutSuccessfully() {
        homePage.clickOnCustomerLoginTab();
        customerLoginPage.clickOnYourName();
        customerLoginPage.selectYourNameOnCustomerPage(customer);
        customerLoginPage.clickOnCustomerLoginButton();

        customerPage.verifyCustomerLoutOutTabIsDisplayed();
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(customerPage.getCustomerLoutOutText().contains("Logout"));
        customerPage.clickOnCustomerLogOutButton();
        customerLoginPage.verifyThatYourNameTextIsDisplayed();
        sa.assertTrue(customerLoginPage.getYourNameTextMessage().contains("Your Name :"));
        customerLoginPage.clickOnHomepageBtn();
    }

    /*
        4. customerShouldDepositMoneySuccessfully.
        click on "Customer Login" Tab
        search customer that you created.
        click on "Login" Button
        click on "Deposit" Tab
        Enter amount 100
        click on "Deposit" Button
        verify message "Deposit Successful"
    */
    @Test(priority = 3, groups= {"regression"})
    public void customerShouldDepositMoneySuccessfully() {

        homePage.clickOnCustomerLoginTab();
        customerLoginPage.clickOnYourName();
        customerLoginPage.selectYourNameOnCustomerPage(customer);
        customerLoginPage.clickOnCustomerLoginButton();
        accountPage.clickOnDepositTab();
        accountPage.enterDepositAmount(depositAmount);
        accountPage.clickOnDepositButton();
        accountPage.verifyThatDepositSuccessfulTextisDisplayed();
        accountPage.getDepositSuccessfulText();
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(accountPage.getDepositSuccessfulText().contains("Customer added successfully"));String expectedText = "Deposit Successful";
        accountPage.clickOnHomepageBtn();
    }

/*          5. customerShouldWithdrawMoneySuccessfully
    click on "Customer Login" Tab
    search customer that you created.
    click on "Login" Button
    click on "Withdrawl" Tab
    Enter amount 50
    click on "Deposit" Button
    verify message "Transaction Successful"
*/

    @Test(priority = 4, groups= {"regression"})
    public void customerShouldWithdrawMoneySuccessfully() throws InterruptedException {
        homePage.clickOnCustomerLoginTab();
        customerLoginPage.clickOnYourName();
        customerLoginPage.selectYourNameOnCustomerPage(customer);
        customerLoginPage.clickOnCustomerLoginButton();
        accountPage.clickOnWithdrawlTab();
        accountPage.enterWithdrawlAmount(withdrawlAmount);
        accountPage.clickOnWithdrawlButton();
        accountPage.verifyThatTransactionSuccessfulTextisDisplayed();
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(accountPage.getTransactionSuccessfulText().contains("Transaction successful"));
        accountPage.clickOnHomepageBtn();
    }

    /* Test No 6 in BankTest class
    bankManagerShouldDeleteCustomerSuccessfully.
    Steps should be
    1. click on Bank Manager Login Tab
    2. click on Customers Tab
    3. search customer by firstname and click on Delete button.
     */

    @Test(priority = 5, groups= {"regression"})
    public void bankManagerShouldDeleteCustomerSuccessfully() {
        homePage.clickOnBankManagerLoginTab();
        bankManagerLoginPage.clickOnCustomersTab();
        customerDetailsPage.searchCustomerByName(firstName);
        customerDetailsPage.clickOnDeleteCustomerButton();
    }

}
