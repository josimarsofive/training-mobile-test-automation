package alicorp.selenium.pages;
import alicorp.selenium.core.DriverFactory;
import alicorp.selenium.locators.BaseLocators;
import alicorp.selenium.locators.LoginLocators;
import alicorp.selenium.wrapper.Driver;
import cucumber.api.Scenario;

import java.io.IOException;

public class LoginPage extends BasePage {

    private DriverFactory factory = new DriverFactory();
    private LoginLocators loginLocators;

    private String getEtiqueta(String xpath, String value) {return xpath.replace("@label", value); }

    public void initDriver(Scenario scenario)throws Exception{
        factory.createDriver(scenario);
        driver = new Driver(factory.driver(),factory.getBrowserName(),factory.getEnvironment(), factory.getOtpType());
        BaseLocators.browserName = driver.getBrowserName();
        BaseLocators.version = driver.getVersion();
        BaseLocators.paquete=driver.getEnvironment();
        loginLocators = new LoginLocators();
    }
    public void takeScreenshot(Scenario scenario) throws IOException{
        if(driver!=null)
            driver.takeScreenShot(scenario);
    }
    public int getTotalRowExamples() {
        if(factory == null)
            return 0;

        return factory.getTotalRowExamples();
    }
    public void closeDriver() {
        factory.tearDown();
    }
    public void closeDevice() throws IOException{
        factory.closeDevice();
    }

    public boolean  validateloginView(){
        System.out.println("Estoy en alimarket");
        driver.elementIsVisible(loginLocators.imgDex);
        return true;
    }

    public void enterClientCode(String code){
        driver.waitForElement(loginLocators.txtCodeClient);
        driver.sendKeys(code,loginLocators.txtCodeClient);

    }

    public void enterClientPassword(String pass){
        driver.waitForElement(loginLocators.txtPassword);
        driver.sendKeys(pass,loginLocators.txtPassword);
    }

    public void clickLogin(){
        driver.waitForElement(loginLocators.btnLogin);;
        driver.click(loginLocators.btnLogin);
    }



}
