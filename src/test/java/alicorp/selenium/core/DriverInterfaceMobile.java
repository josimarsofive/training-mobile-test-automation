package alicorp.selenium.core;

import io.appium.java_client.MobileDriver;

import java.io.IOException;

public abstract class DriverInterfaceMobile {
    @SuppressWarnings("rawtypes")
    public abstract void instanceRemoteDriver();
    @SuppressWarnings("rawtypes")
    public abstract MobileDriver driver();
    public abstract void freeDriver();
    protected abstract void setDriverPath();
    public abstract void closeDevice() throws IOException;

}
