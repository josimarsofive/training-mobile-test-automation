package alicorp.userstory.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumberHtmlReport",
                "json:target/cucumber-report.json"
        },
        glue = {"alicorp/userstory/steps"},
        features = "src/test/java/alicorp/userstory/login/AM1_Login.feature",
        tags = {"~@ANDROID", "@uat","@android","@AM1_001"}
)
public class JosimarRunner {
}
