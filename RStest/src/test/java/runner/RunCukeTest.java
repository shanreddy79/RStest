package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * "RunCukeTest" class file is Runner file
 *
 * @author shan.reddy
 */

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty",
        "html:target/test-report",
        "json:target/test-report.json",
        "junit:target/test-report.xml"},
        features={"src/test/resources/features/"},glue={"steps","implementation"},tags={"@Positive"})

public class RunCukeTest {

}