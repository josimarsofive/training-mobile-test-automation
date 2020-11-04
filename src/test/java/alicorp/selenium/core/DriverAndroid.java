package alicorp.selenium.core;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverAndroid extends DriverInterfaceMobile {

    @SuppressWarnings("rawtypes")
    private MobileDriver driver;

    @SuppressWarnings("rawtypes")
    private WebDriverWait wait;
    private AppiumDriverLocalService appium;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private String avdPort = "";
    private Boolean isEmulator = true;
    private Boolean useAppiumIDE=false;
    private String appiumURL = "";
    private String useAppiumPort="4723";
    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void instanceRemoteDriver(){
        avdPort = Resources.getProperty("device", "CAP_ANDROID_PORT").trim();
        isEmulator = Boolean.parseBoolean(Resources.getProperty("device", "IS_EMULATOR"));
        useAppiumIDE=Boolean.parseBoolean(Resources.getProperty("device","USE_APPIUM_IDE"));
        useAppiumPort= Resources.getProperty("device", "USE_APPIUM_PORT").trim();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Resources.getProperty("device","CAP_ANDROID_PLATFORMNAME"));
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Resources.getProperty("device","CAP_ANDROID_DEVICENAME"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Resources.getProperty("device", "CAP_ANDROID_VERSION"));

        capabilities.setCapability(MobileCapabilityType.APP, Resources.getProperty("device", "CAP_ANDROID_APP_PATH"));
        capabilities.setCapability("--session-override",true);
        capabilities.setCapability("automationName","uiautomator2");
        capabilities.setCapability("autoGrantPermissions", true);

        AppiumServiceBuilder builder;
        try {
            appiumURL = "http://127.0.0.1:" + useAppiumPort +"/wd/hub";
            if(!useAppiumIDE) {
                builder = new AppiumServiceBuilder();
                builder.withIPAddress("127.0.0.1");
                builder = builder.usingAnyFreePort();
                appium = AppiumDriverLocalService.buildService(builder);
                appium.start();
                appiumURL = appium.getUrl().toString();
            }

            if(isEmulator) {
                capabilities.setCapability("avd", Resources.getProperty("device", "CAP_ANDROID_DEVICENAME"));
                capabilities.setCapability("avdArgs", "-port " + avdPort);
                capabilities.setCapability("isHeadless",Boolean.parseBoolean(Resources.getProperty("device", "CAP_ANDROID_ISHEADLESS").trim()));
            }

            driver = new AndroidDriver<>(new URL(appiumURL), capabilities);
            driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 600);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public MobileDriver driver() {
        return driver;
    }

    @Override
    public void freeDriver() {
        driver.closeApp();
        driver.quit();
        if (!useAppiumIDE) {
            appium.stop();
        }

    }

    @Override
    public void closeDevice() throws IOException {
        if(!isEmulator)
            return;

        Runtime.getRuntime().exec("adb -s emulator-" + avdPort +" emu kill");
    }


    @Override
    protected void setDriverPath() {
    }




}