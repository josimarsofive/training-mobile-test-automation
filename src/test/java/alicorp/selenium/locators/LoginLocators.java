package alicorp.selenium.locators;

import org.openqa.selenium.By;

public class LoginLocators extends BaseLocators {

    public By txtCodeClient;
    private String TXT_CODE_CLIENT;
    public By txtPassword;
    private String TXT_PASSWORD;
    public By btnLogin;
    private String BTN_LOGIN;
    public By imgDex;
    private String IMG_DEX;
    public By linkViewCodigoClient;
    public String LINK_VIEW_CODIGO_CLIENT;
    public By linkForgotPassword;
    private String LINK_FORGOT_PASWORD;
    public By linkTermsConditions;
    private String LINK_TERMS_CONDITIONS;

public LoginLocators(){
    switch(browserName){
        case "android":
        setAndroidLocators();
        break;
        case "ios":
        setIOSLocators();
        break;
}
}

    public void setAndroidLocators(){

        IMG_DEX = paquete + ":id/image_dex_logo";
        imgDex = By.id(IMG_DEX);

        TXT_CODE_CLIENT =paquete + ":id/input_editText_code";
        txtCodeClient=By.id(TXT_CODE_CLIENT);

        TXT_PASSWORD=paquete + ":id/inputEditText_password";
        txtPassword=By.id(TXT_PASSWORD);

        BTN_LOGIN =paquete + ":id/button_loader";
        btnLogin=By.id(BTN_LOGIN);

        LINK_VIEW_CODIGO_CLIENT = "//android.widget.TextView[contains(@text,'¿Dónde ubico mi código de cliente?')]";

        LINK_FORGOT_PASWORD=paquete + ":id/text_forgot_password";
        linkForgotPassword=By.id(LINK_FORGOT_PASWORD);

        LINK_TERMS_CONDITIONS=paquete + ":id/text_terms_and_conditions";
        linkTermsConditions=By.id(LINK_TERMS_CONDITIONS);

    }

    public void setIOSLocators(){

    }

}
