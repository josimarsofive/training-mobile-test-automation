package alicorp.selenium.pages;

import alicorp.selenium.core.DriverFactory;
import alicorp.selenium.locators.BaseLocator;
import alicorp.selenium.locators.LoginLocator;
import alicorp.selenium.wrapper.Driver;
import cucumber.api.Scenario;

import java.io.IOException;

public class LoginPage extends BasePage {

    private DriverFactory factory = new DriverFactory();
    private LoginLocator loginLocator;

    public void initDriver(Scenario scenario) throws Exception {
        factory.createDriver(scenario);
        driver = new Driver(factory.driver(),factory.getBrowserName(), factory.getEnvironment(), factory.getOtpType());
        BaseLocator.browserName = driver.getBrowserName();
        BaseLocator.version = driver.getVersion();
        BaseLocator.paquete = driver.getEnvironment();
        loginLocator = new LoginLocator();
    }

    public void takeScreenshot(Scenario scenario) throws IOException {
        if(driver!=null)
            driver.takeScreenShot(scenario);

    }
    public int getTotalRowExamples(){
        if(factory==null)
            return 0;
        return factory.getTotalRowExamples();
    }

    public void closeDriver(){
        factory.tearDown();
    }

    public void closeDevice() throws IOException {
        factory.closeDevice();
    }

  public boolean validateLogin(){
        driver.elementIsPresent(loginLocator.txtCodeClient);
        return true;
  }

  public void enterClientCode(String code){
        driver.waitForElement(loginLocator.txtCodeClient);
        driver.sendKeys(code,loginLocator.txtCodeClient);

  }

    public void enterClientPass(String pass){
        driver.waitForElement(loginLocator.txtPassClient);
        driver.sendKeys(pass,loginLocator.txtPassClient);
    }

    public void clickLogin(){
       driver.waitForElement(loginLocator.btnLogin);
       driver.click(loginLocator.btnLogin);
    }


}
