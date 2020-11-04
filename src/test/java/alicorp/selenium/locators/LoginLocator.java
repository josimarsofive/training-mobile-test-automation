package alicorp.selenium.locators;

import org.openqa.selenium.By;

public class LoginLocator extends BaseLocator{

    public By txtCodeClient;
    public String TXT_CODE_CLIENT;

    public By txtPassClient;
    public String TXT_PASS_CLIENT;

    public By btnLogin;
    public String BTN_LOGIN;

    public LoginLocator(){
        switch (browserName){
            case "android":
                setAndroidLocators();
                break;

            case "ios":
                setIOSLocators();
                break;

        }
    }

    public void setAndroidLocators(){
        TXT_CODE_CLIENT = paquete + ":id/input_editText_code";
        txtCodeClient = By.id(TXT_CODE_CLIENT);

        TXT_PASS_CLIENT = paquete + ":id/inputEditText_password";
        txtPassClient = By.id(TXT_PASS_CLIENT);

        BTN_LOGIN = paquete + ":id/button_loader";
        btnLogin = By.id(BTN_LOGIN);


    }

    public void setIOSLocators(){

    }








   // com.alicorp.alimarket.qa:id/input_editText_code

    //com.alicorp.alimarket.qa:id/inputEditText_password

    //com.alicorp.alimarket.qa:id/button_loader
}
