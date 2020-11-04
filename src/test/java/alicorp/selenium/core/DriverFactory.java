package alicorp.selenium.core;

import cucumber.api.Scenario;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.junit.ExecutionUnitRunner;
import cucumber.runtime.junit.JUnitReporter;
import cucumber.runtime.model.*;
import gherkin.formatter.model.Examples;
import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class DriverFactory {

    private DriverInterfaceMobile genericDriverMovil;
    private String browserName = "";
    private String otpType = "";
    private String environment="";
    private int totalRowExamples = 0;
    //private Map<Integer,String> steps= new HashMap<Integer, String>();

    public void createDriver(Scenario scenario) throws  Exception{
        readTagsRunner(scenario);
        otpType = Resources.getProperty("device", "OTP_TYPE");
        switch (browserName){
            case "android":
                genericDriverMovil = new DriverAndroid();
                break;
            case "ios":
                genericDriverMovil = new DriverIOS();
                break;
            default:
                genericDriverMovil = null;
                throw new WebDriverException(driverExceptionMessage(browserName));
        }

        genericDriverMovil.instanceRemoteDriver();
    }

    public void tearDown(){
        genericDriverMovil.freeDriver();
    }

    public void closeDevice() throws IOException {
        genericDriverMovil.closeDevice();
    }


    @SuppressWarnings("rawtypes")
    public MobileDriver driver(){
        return genericDriverMovil.driver();
    }

    private String driverExceptionMessage(String browserName) {
        StringBuilder builder = new StringBuilder();
        String exceptionMsg = "\n[No se ha indicado un explorador valido para inicializar el webdriver]\n";
        String actualMsg = "- Se especifico \"" + browserName +"\"\n";
        String expectedMsg = "- Se esperaba \"android\", o \"iOS\"";
        builder.append(exceptionMsg);
        builder.append(actualMsg);
        builder.append(expectedMsg);
        return builder.toString();
    }

    private String modeExceptionMessage(String mode) {
        StringBuilder builder = new StringBuilder();
        String exceptionMsg = "\n[No se ha indicado un modo de ejecucion valido para inicializar el webdriver]\n";
        String actualMsg = "- Se especifico \"" + mode +"\"\n";
        String expectedMsg = "- Se esperaba \"local\", o \"remote\"";
        builder.append(exceptionMsg);
        builder.append(actualMsg);
        builder.append(expectedMsg);
        return builder.toString();
    }

    public String getOtpType() { return this.otpType; }
    public String getBrowserName(){
        return this.browserName;
    }
    public String getEnvironment(){return this.environment;}

    public int getTotalRowExamples() { return this.totalRowExamples; }

    //public Map<Integer,String> getTotalSteps(){ return this.steps;}

    protected void readTagsRunner(Scenario scenario) throws Exception{

        Field f = scenario.getClass().getDeclaredField("reporter");
        f.setAccessible(true);
        JUnitReporter reporter = (JUnitReporter)f.get(scenario);
        Field executionRunnerField = reporter.getClass().getDeclaredField("executionUnitRunner");
        executionRunnerField.setAccessible(true);
        ExecutionUnitRunner executionUnitRunner = (ExecutionUnitRunner)executionRunnerField.get(reporter);
        Field runtimeField = executionUnitRunner.getClass().getDeclaredField("runtime");
        runtimeField.setAccessible(true);
        Runtime runtime = (Runtime) runtimeField.get(executionUnitRunner);
        Field resourceLoaderField = runtime.getClass().getDeclaredField("resourceLoader");
        resourceLoaderField.setAccessible(true);
        ResourceLoader resourceLoader = (ResourceLoader)resourceLoaderField.get(runtime);
        Field runtimeOptionsField = runtime.getClass().getDeclaredField("runtimeOptions");
        runtimeOptionsField.setAccessible(true);
        RuntimeOptions runtimeOptions = (RuntimeOptions)runtimeOptionsField.get(runtime);
        for(Object obj: runtimeOptions.getFilters()) {
            switch (obj.toString().trim()) {
                case "~@ANDROID":
                    browserName = "android";
                    break;
                case "~@IOS":
                    browserName = "ios";
                    break;
                default:
                    break;
            }
        }
        for(Object obj: runtimeOptions.getFilters()){
            switch (obj.toString().trim()) {
                case "@prod":
                    environment = "com.alicorp.alimarket";
                    break;
                case "@uat":
                    environment = "com.alicorp.alimarket.qa";
                    break;
                case "@desa":
                    environment = "com.alicorp.alimarket.debug";
                break;
                default:
                    break;

            }
        }

        Field cucumberScenarioField = executionUnitRunner.getClass().getDeclaredField("cucumberScenario");
        cucumberScenarioField.setAccessible(true);
        CucumberScenario cucumberScenario = (CucumberScenario) cucumberScenarioField.get(executionUnitRunner);
        Field cucumberFeatureField = cucumberScenario.getClass().getSuperclass().getSuperclass().getDeclaredField("cucumberFeature");
        cucumberFeatureField.setAccessible(true);
        CucumberFeature cucumberFeature = (CucumberFeature) cucumberFeatureField.get(cucumberScenario);
        Field cucumberTagStatementField = cucumberFeature.getClass().getDeclaredField("cucumberTagStatements");
        cucumberTagStatementField.setAccessible(true);
        List<CucumberTagStatement> cucumberTagStatement = (List<CucumberTagStatement>) cucumberTagStatementField.get(cucumberFeature);
        for(CucumberTagStatement tagStatement:cucumberTagStatement) {
            CucumberScenarioOutline scenarioOutline = (CucumberScenarioOutline) tagStatement;
            List<CucumberExamples> examples = scenarioOutline.getCucumberExamplesList();
            for(CucumberExamples ex:examples) {
                Examples example = ex.getExamples();
                totalRowExamples = totalRowExamples + (example.getRows().size() - 1);
            }
        }
    }
}
