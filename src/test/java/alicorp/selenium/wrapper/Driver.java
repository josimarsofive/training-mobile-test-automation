package alicorp.selenium.wrapper;

import alicorp.selenium.core.Resources;
import cucumber.api.Scenario;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Driver {

    private MobileDriver<WebElement> driver;
    private String timeOutString = "60";
    private final int TIMEOUT = Integer.parseInt(timeOutString);
    private Select dropdown;
    private Dimension size;
    private String browserName;
    private String version;
    private String environment;
    private String otpType;


    public Driver(MobileDriver<WebElement> driver, String browserName, String environment, String otpType) {
        this.driver = driver;
        this.browserName = browserName;
        this.environment=environment;
        this.otpType = otpType;
        setVersion();
    }

    private void setVersion(){
        if(this.browserName.toLowerCase().equals("android"))
            version = Resources.getProperty("device","CAP_ANDROID_VERSION");
        else
            version = Resources.getProperty("device","CAP_IOS_PLATFORMVERSION");
    }

    public void launch(String url) {
        driver.get(url);
    }

    //@Deprecated
    public void scrollAndroid(){
        int scrollYStart = (int) (getSizePage().getHeight() * 0.43224);
        int scrollEnd = (int) (getSizePage().getHeight() * 0.34583);
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(0, scrollYStart));
        action.moveTo(PointOption.point(0, -scrollEnd)).release().perform();
    }
    public void scrollNotificaciones(){
        int scrollYStart = (int) (getSizePage().getHeight() * 0.0);
        int scrollEnd = (int) (getSizePage().getHeight() * 0.8);
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(0, scrollYStart))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(0,scrollEnd)).release().perform();

    }
    public void scrollOcultarNotificaciones(){
        int scrollYStart = (int) (getSizePage().getHeight() * 0.8);
        int scrollEnd = (int) (getSizePage().getHeight() * 0);
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(0, scrollYStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(0,scrollEnd)).release().perform();

    }
    public void scrollLateralNotificaciones(By elemento){
        int xstar1= (int) (getSizePage().getWidth()*0.5);
        int xsend1= (getSizePage().getWidth());
        TouchAction action3 = new TouchAction(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(driver.findElement(elemento))));
        action3.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(xsend1,xstar1)).release().perform();
    }
    public void scrollDinamicoAndroid(double YStart,double YEnd){
        int scrollYStart = (int) (getSizePage().getHeight() * YStart);
        int scrollEnd = (int) (getSizePage().getHeight() * YEnd);
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(0, scrollYStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(0,scrollEnd)).release().perform();

    }
    public void verticalScroll(By from, By to){
        int startY = driver.findElement(from).getLocation().getY();
        int endY = driver.findElement(to).getLocation().getY();

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(0, endY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .moveTo(PointOption.point(0,startY)).release().perform();
    }


    public void androidSeekBarPorcentaje(By seekElement, float porcentaje){
        WebElement seek_bar=driver.findElement(seekElement);
// get start co-ordinate of seekbar
        int start=seek_bar.getLocation().getX();
//Get width of seekbar
        int end=seek_bar.getSize().getWidth();
//get location of seekbar vertically
        int y=seek_bar.getLocation().getY();

        int endy=seek_bar.getSize().getHeight();

        y = y + endy/2;

        // Select till which position you want to move the seekbar
        TouchAction action=new TouchAction(driver);


        //Move it 40%
        int moveTo=(int)(start + end*porcentaje/100);


        action.press(PointOption.point(start,y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(moveTo,y)).release().perform();

    }


    public void androidSeekBar (By xpath, int index)
    {
        WebElement slider = driver.findElement(xpath);

        try {
            Actions builder = new Actions(driver);
            for (int i = 0; i < index; i++) {
                builder.moveToElement(slider).click(slider).sendKeys(Keys.ARROW_DOWN).perform();

            }
        }
        catch(Exception ex)
        {
            System.out.println("Error en androidSeekBar: " + ex.toString());
        }

    }

    public void navigateTo(String ruta) {
        driver.navigate().to(ruta);
    }

    public void maximizarPantalla() {
        driver.manage().window().maximize();
    }

    public String pageURL() {
        return driver.getCurrentUrl();
    }

    public WebElement find(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            //driver.quit();
            throw new NoSuchElementException(
                    "No se ha encontrado ningun elemento web en la ruta indicada: "
                            + locator);
        }
        return element;
    }

    public List<WebElement> findElements(By locator) {
        List<WebElement> element = null;
        try {
            element = driver.findElements(locator);
        } catch (NoSuchElementException e) {
            //driver.quit();
            throw new NoSuchElementException(
                    "No se ha encontrado ningun elemento web en la ruta indicada: "
                            + locator);
        }
        return element;
    }

    public WebElement findExists(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                    "No se ha encontrado ningun elemento web en la ruta indicada: "
                            + locator);
        }
        return element;
    }

    @SuppressWarnings("unused")
    public boolean isContainerOpen(By containerLocator) {
        WebElement element;
        try {
            element = findExists(containerLocator);

        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void sendKeys(String text, By locator) {

        find(locator).sendKeys(text);
    }

    public String getVisibleText(By locator) {
        return find(locator).getText();
    }

    public String getVisibleTextScrolling(By locator) {
        while ((find(locator).isDisplayed())==false){
            scrollAndroid();

        }
        return find(locator).getText();

    }




    public ArrayList<String> getVisibleTexts(By locator) {
        final List<WebElement> elements = driver.findElements(locator);
        final ArrayList<String> lista = new ArrayList<String>();
        for (final WebElement webElement : elements) {
            lista.add(webElement.getText());
        }
        return lista;
    }

    public String getValue(By locator) {
        return find(locator).getAttribute("value");
    }

    public String getValue(By locator, String attributeName) {
        return find(locator).getAttribute(attributeName);
    }

    public boolean getValueChecked(By locator, String attributeName) {
        boolean valor = false;
        if (attributeName.equals("checked")) {
            find(locator).getAttribute(attributeName);
            valor= true;
        }
        return valor;
    }

    public void click(By locator) {
        find(locator).click();
    }

    public void mouseOverAndSelect(By menuLocator) {
        Actions action = new Actions(driver);
        action.moveToElement(find(menuLocator)).perform();
        sleep(1);
    }

    public void mouseOver(By menuLocator) {
        ((JavascriptExecutor)driver).executeScript("window.focus();");
        Actions action = new Actions(driver);
        action.moveToElement(find(menuLocator)).perform();
        sleep(1);
    }

    /**
     * Lo que hace este metodo es hacer click en un element web (boton, link,
     * etc) y luego espera a que el elemento a ser evaluado desaparezca en
     * pantalla, para luego esperar a que vuelva a aparecer (refresh) para que
     * continue con un flujo determinado. OJO: Utilizar solo en casos de
     * refrescado ocasionados por llamadas ajax o javascript.
     *
     * @param clickedLocator
     * @param expectedLocator
     * @param seconds
     */
    public void clickAndWaitForElement(By clickedLocator, By expectedLocator,
                                       int... seconds) {
        find(clickedLocator).click();

        int timeout = 0;
        if (seconds == null) {
            timeout = TIMEOUT;
        } else {
            if (seconds.length > 1) {
                throw new WebDriverException(
                        "Se esperaban solo 3 parametros");
            } else {

                timeout = seconds[0];
            }
        }
        Wait<WebDriver> wait;
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(expectedLocator));
    }

    /**
     * Espera un locator para formar un elemento web y hacerle click Una vez
     * hecho el click, espera hasta que la pagina web cargue en su totalidad.
     * Solamente utilizar cuando se sepa de antemano que al hacer click se
     * cargara toda la pantalla. No aplica para esperas javascript o ajax.
     *
     * @param locator
     */
    public void clickAndWait(By locator) {
        find(locator).click();
        waitForPageToLoad();
    }

    /**
     * Este metodo aplica para esperas javascript o ajax. Se debe proporcionar
     * el tiempo maximo de espera hasta que cargue un elemento despues de una
     * llamada javascript o ajax
     *
     * @param clickedLocator
     * @param seconds
     */
    public void clickAndWait(By clickedLocator, int seconds) {
        find(clickedLocator).click();
        sleep(seconds);
    }

    /**
     * Indica si un elemento web (generado por el locator esperado) esta visible
     * en pantalla. Un elemento web puede estar presente en el html pero no
     * necesariamente visible para el usuario. Para determinar si un elemento
     * esta presente en el html, utilizar {@link #elementIsPresent(By locator)}
     *
     * @param locator
     * @return boolean
     */
    public boolean elementIsVisible(By locator) {
        return find(locator).isDisplayed();
    }

    /**
     * Indica si un element web (generado por el locator esperado) esta presente
     * en el html de la pagina web. En caso se desee validar si un elemento web,
     * ademas de estar presente, es visible por el usuario, utilizar
     * {@link #elementIsVisible(By locator)}
     *
     * @param locator
     * @return boolean
     */
    public boolean elementIsPresent(By locator) {
        try {
            if(locator == null)
                return false;

            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new WebDriverException(
                    "Error inesperado mientras el hilo con id="
                            + Thread.currentThread().getId()
                            + " estaba en espera");
        }
    }

    public void sleepminute(int minute){
        try {
            Thread.sleep(minute * 60000);
        } catch (InterruptedException e){
            throw new WebDriverException(
                    "Error inesperado mientras el hilo con id="
                            + Thread.currentThread().getId()
                            + " estaba en espera");
        }
    }

    public void waitForElement(By expectedLocator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(expectedLocator));
    }

    public void hideKeyboard(){
        try {
            driver.hideKeyboard();
        }catch (Exception e){
            System.out.println("Keyboard is already hidden");
        }
    }

    public void waitForPageToLoad() {
        Wait<WebDriver> wait = new WebDriverWait(driver, TIMEOUT);
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript(
                        "return document.readyState").equals("complete");
            }
        };
        try {
            wait.until(expectation);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public String getPageSource(){
        return driver.getPageSource();
    }

    public void executeJS(String script) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (jse instanceof WebDriver) {
            jse.executeScript(script);
        }
    }

    /**
     * Metodo que simila el moviendo de un scroll se tiene que enviar como
     * parametro el ultimo locato no visible en la grilla.
     */
    public void moverScroll(By locatorNoVisible) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", find(locatorNoVisible));
    }

    public void enterKeys(By locator) {
        find(locator).sendKeys(Keys.ENTER);
        sleep(2);
    }

    public void tabKeys(By locator) {
        find(locator).sendKeys(Keys.TAB);
        sleep(2);
    }

    public void clear(By locator) {
        find(locator).click();
        find(locator).clear();
    }

    public void waitForElementIsNotVisible(By expectedLocator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(expectedLocator));

    }

    public void waitForElementIsInvisibility(By expectedLocator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions
        .invisibilityOfElementLocated(expectedLocator));
    }

    public boolean elementIsEnabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }

    public boolean isChecked(By locator) {
        return find(locator).isSelected();
    }

    public boolean elementIsSelected(By locator) {
        return find(locator).isSelected();
    }

    public WebElement waitUntilAppearAndFind(final By by) {
        final WebDriverWait wait = new WebDriverWait(driver, 30);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<String> getItems(By dropdownLocator) {
        dropdown = new Select(find(dropdownLocator));
        List<WebElement> options = dropdown.getOptions();
        List<String> optionTexts = new ArrayList<String>();
        for (Iterator<WebElement> iterator = options.iterator(); iterator
                .hasNext();) {
            WebElement option = (WebElement) iterator.next();
            optionTexts.add(option.getText());
        }
        return optionTexts;
    }

    public String getItem(By dropdownLocator, int index) {
        return getItems(dropdownLocator).get(index);
    }

    public String getItemSelected(By dropdownLocator) {
        dropdown = new Select(find(dropdownLocator));
        return dropdown.getFirstSelectedOption().getText();
    }

    public void selectItem(By dropdownLocator, int index) {
        dropdown = new Select(find(dropdownLocator));
        dropdown.selectByIndex(index);
    }

    public void takeScreenShot(Scenario scenario) throws IOException{
        byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }


    public void waitPresenceOfAllElements(By expectedLocator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(expectedLocator));
    }

    public void pressAndMoveToElement(double startDX, double startDY, double endDX, double endDY) {

        size = getSizePage();
        System.out.println("Tamanio page: " + size);
        int startX = (int) (size.width * startDX);
        System.out.println("Tamanio page: " + startX);
        int endX = (int) (size.width * startDY);
        System.out.println("Tamanio page: " + endX);

        int startY = (int) (size.height * endDX);
        System.out.println("Tamanio page: " + startY);
        int endY = (int) (size.height * endDY);
        System.out.println("Tamanio page: " + endY);

        TouchAction action = new TouchAction(driver);
        action.longPress(PointOption.point(startX, startY));
        action.moveTo(PointOption.point(endX, endY)).release().perform();
        //action.longPress(startX, startY);
        //action.moveTo(endX, endY).release().perform();
    }

    public void pressAppAndMoveToElement(double startDX, double startDY, double endDX, double endDY) {
        int startX = (int) (startDX);
        int endX = (int) (startDY);
        int startY = (int) (endDX);
        int endY = (int) (endDY);

        TouchAction action = new TouchAction(driver);
        action.longPress(PointOption.point(startX, startY));
        action.moveTo(PointOption.point(endX, endY)).release().perform();
        //action.longPress(startX, startY);
        //action.moveTo(endDX, endDY).release().perform();
    }




    /**
     * Scrollea hasta la parte inferior de la pantalla
     * De acuerdo a las proporciones de la pantalla se obtiene un porcentaje aproximado
     */
    public void scrolliOS() {
        int scrollXStart = (int) (getSizePage().getWidth() * 0.2906);
        int scrollYStart = (int) (getSizePage().getHeight() * 0.4648);
        int scrollEnd = (int) (getSizePage().getHeight() * 0.4648);

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(scrollXStart, scrollYStart));
        action.moveTo(PointOption.point(0, -scrollEnd)).release().perform();
        //action.press(scrollXStart, scrollYStart);
        //action.moveTo(0, -scrollEnd).release().perform();
    }

    public Dimension getSizePage(){
        Dimension size = driver.manage().window().getSize();
        return size;
    }

    public void backPage(){
       driver.navigate().back();

       // ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    public void closeKeyboardiOS(){
        click(By.id("Done"));
    }

    public void pressElement(int startX, int startY){
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX, startY)).perform();
        //action.press(startX,startY).release().perform();
    }
    public void returnToApp_Android() { this.driver.activateApp(getEnvironment());}

    public void  app_switchToApp_Android()
    {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
        sleep(2);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH
        ));
    }
    public void  app_TABToApp_Android()
    {
        sleep(2);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
        sleep(1);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
        sleep(1);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
        sleep(1);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
        sleep(1);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
    public void returnToApp_iOS(){
        this.driver.activateApp("pe.com.scotiabank.ibanking.joy.rc");
    }
    public String getBrowserName() {
        return browserName;
    }

    public String getVersion() {
        return this.version;
    }
    public String getEnvironment() {
        return this.environment;
    }
    public String getOtpType() { return  this.otpType; }

    public static boolean isDisplayed(WebElement element) {
        try {
            if(element.isDisplayed())
                return element.isDisplayed();
        }catch (NoSuchElementException ex) {
            return false;
        }
        return false;
    }

    public void switchToChrome() {
        this.driver.activateApp("com.android.chrome");
    }

    public void switchToSafari() {

    }

    public void sendEnterAndroid() {

        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

    }

    public void sendTopAndroid() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));

    }

    public void sendTabAndroid() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));

    }

    public void sendEscAndroid() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ESCAPE));

    }

    public void sendescribirborraruncaracterAndroid() {
        //((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.SPACE));
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.A));
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DEL));
    }




}
