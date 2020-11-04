package alicorp.userstory.steps;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.IOException;

public class LoginSteps extends BasePageSteps {

    private static int executedRowExamples = 0;

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        loginPage.initDriver(scenario);
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            loginPage.takeScreenshot(scenario);
        }
        loginPage.closeDriver();
        if(executedRowExamples<loginPage.getTotalRowExamples()){
            loginPage=null;
            return;
        }
        loginPage.closeDevice();
    }


    @Given("^Alimarket App se encuentra abierta$")
    public void alimarket_App_se_encuentra_abierta(){
      Assert.assertTrue("No se esta ejecutando el app alimarket", loginPage.validateLogin() );

    }

    @When("^ingresar \"([^\"]*)\" como el usuario en la vista login dentro del flujo login\\.$")
    public void ingresarComoElUsuarioEnLaVistaLoginDentroDelFlujoLogin(String code){
        loginPage.enterClientCode(code);

    }

    @And("^ingresar \"([^\"]*)\" como contraseña en la vista login dentro del flujo login\\.$")
    public void ingresarComoContraseñaEnLaVistaLoginDentroDelFlujoLogin(String pass) throws Throwable {
       loginPage.enterClientPass(pass);
    }


    @And("^seleccionar el botón \"([^\"]*)\" en la vista login dentro del flujo login\\.$")
    public void seleccionarElBotónEnLaVistaLoginDentroDelFlujoLogin(String arg0) throws Throwable {
       loginPage.clickLogin();
    }
}
