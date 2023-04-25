package com.digital.ui.pages;

import com.digital.ui.driver.Driver;
import com.digital.ui.element_helper.WebElementActions;
import com.digital.utils.FakeDataProvider;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public WebElementActions elementActions = new WebElementActions();

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


}
