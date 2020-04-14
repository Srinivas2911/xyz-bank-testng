package com.bank.pages;

import com.bank.utility.Utility;
import org.openqa.selenium.By;

public class CustomerDetailsPage extends Utility {

    By searchCustomerField = By.xpath("//input[@placeholder='Search Customer']");
    By deleteCustomerBtn = By.xpath("//button[contains(text(),'Delete')]");

    public void searchCustomerByName (String firstName){
        clickOnElement(searchCustomerField);
        sendTextToElement(searchCustomerField);
            }

            public void clickOnDeleteCustomerButton (){
        clickOnElement(deleteCustomerBtn);
            }

}
