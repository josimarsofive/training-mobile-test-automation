package alicorp.selenium.core;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class DriverIOS extends DriverInterfaceMobile {

    @SuppressWarnings("rawtypes")
    private MobileDriver driver;

    @SuppressWarnings("rawtypes")
    private WebDriverWait wait;

    private AppiumDriverLocalService appium;
    private Boolean isSimulator = true;

    @SuppressWarnings("rawtypes")
    @Override
    public void instanceRemoteDriver() {
        isSimulator = Boolean.parseBoolean(Resources.getProperty("device", "IS_SIMULATOR"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Resources.getProperty("device", "CAP_IOS_PLATFORMNAME"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Resources.getProperty("device", "CAP_IOS_VERSION"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Resources.getProperty("device", "CAP_IOS_DEVICENAME"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(MobileCapabilityType.APP, Resources.getProperty("device", "CAP_IOS_APP_PATH"));
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability("useNewWDA", true);
        capabilities.setCapability("showXcodeLog", true);
        capabilities.setCapability("autoLaunch", true);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("--session-override", true);

        if(!isSimulator){
            capabilities.setCapability(MobileCapabilityType.UDID, Resources.getProperty("device", "CAP_IOS_UDID"));
        }

        try {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withIPAddress("127.0.0.1");
            builder = builder.usingAnyFreePort();
            appium = AppiumDriverLocalService.buildService(builder);
            appium.start();

            driver = new IOSDriver(new URL(appium.getUrl().toString()), capabilities);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 15);
        } catch (Exception e) {
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
        appium.stop();
    }

    @Override
    public void closeDevice() throws IOException {
        if(!isSimulator)
            return;

        String iosVersion = Resources.getProperty("device", "CAP_IOS_VERSION").trim();
        String deviceName = Resources.getProperty("device", "CAP_IOS_DEVICENAME").trim();
        Process p = new ProcessBuilder("bash", "-c", "xcrun simctl list | grep '\\-- iOS " + iosVersion + "' -A60  | grep -m 1 '" + deviceName + " (' | awk 'match($0, /\\(([-0-9A-F]+)\\)/) { print substr( $0, RSTART + 1, RLENGTH - 2 )}'").start();
        String uuid = IOUtils.toString(p.getInputStream(), Charset.defaultCharset()).trim();

        Runtime.getRuntime().exec("xcrun simctl shutdown " + uuid);
    }

    @Override
    protected void setDriverPath() {

    }
}
