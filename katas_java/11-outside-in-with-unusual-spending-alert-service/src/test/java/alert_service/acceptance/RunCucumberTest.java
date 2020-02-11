package alert_service.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber-report.json", "pretty"},
        features = {"src/test/resources/features"},
        glue = {"alert_service/acceptance"})
public class RunCucumberTest {
}
