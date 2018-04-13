package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import utilities.TestDataPrep;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * "Stepdefs.java" class file handles all the implementation code for steps written in "Automation_HomePage.feature" file
 *
 * @author shan.reddy
 */
public class Stepdefs extends TestDataPrep {
    public Scenario scen;
    public WebDriver driver;
    public HomePage hp;

    @Before
    public void before(Scenario scenario) {
        this.scen = scenario;
    }

    @After
    public void after() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }
    }

    /**
     * Will select the expected BrowserType to run the scenario
     *
     * @param browserType:Expected BrowserType
     */
    @Given("^I want to run in \"([^\"]*)\"$")
    public void i_want_to_run_in(String browserType) throws Throwable {
        switch (browserType) {
            case "chrome": {
                String exePath = "src/test/resources/chromedriver/chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", exePath);
                this.driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            }

            case "headlessChrome": {
                String exePath = "src/test/resources/chromedriver/chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", exePath);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                this.driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1550, 500));
                driver.navigate().refresh();
                break;
            }
        }
        hp = PageFactory.initElements(driver, HomePage.class);
    }

    /**
     * Will be navigating/landing expected page
     *
     * @param urlType: Expected page
     */
    @When("^I am on \"([^\"]*)\"$")
    public void i_am_on(String urlType) throws Throwable {
        driver.get(urlType);
    }

    /**
     * Will verify whether expected textlink is been clicked
     *
     * @param expTextLink:Expected TextLink
     */

    @Given("^I click TextLink: \"([^\"]*)\"$")
    public void i_click_TextLink(String expTextLink) throws Throwable {
        try {
            String expLoc = expTextLink.replaceAll("\\s+", "");
            waitForElement(driver, hp.homePageLocs(expLoc)).click();

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String err = sw.toString();
            scen.write(err);
            throw new Exception();
        }
    }

    /**
     * Will Verify Expected  Text is displaying or not
     *
     * @param expText:Expected Text
     */
    @Then("^The page should display header:\"([^\"]*)\" option$")
    public void the_page_should_display_header_option(String expText) throws Throwable {
        try {
            String expLoc = expText.replaceAll("\\s+", "");
            waitForElement(driver, hp.homePageLocs(expLoc)).click();
            scen.write("Actual Text :" + driver.findElement(hp.homePageLocs(expLoc)).getText());
            scen.write("Expected Text :" + expText);
            assertTrue(expText.trim().replaceAll("\\s+", " ").contains(driver.findElement(hp.homePageLocs(expLoc)).getText()));

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String err = sw.toString();
            scen.write(err);
            throw new Exception();
        }
    }

    /**
     * Will verify whether expected page is been Displayed/Navigated or not
     *
     * @param pathParameter :Expected Page
     */

    @Then("^I am taken to \"([^\"]*)\"$")
    public void i_am_taken_to(String pathParameter) throws Throwable {
        try {
            Thread.sleep(3000);
            String url = driver.getCurrentUrl();
            URL aURL = new URL(url);
            scen.write("Actual Displayed page :" + aURL.getPath());
            scen.write("Expected Displayed Page :" + pathParameter);
            assertTrue(aURL.getPath().contains(pathParameter));
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String err = sw.toString();
            scen.write(err);
            throw new Exception();
        }
    }

    /**
     * Will be Verifying Expected options under categories is been displayed
     *
     * @param expOptions:Expected options
     */
    @Then("^The page should display Categories Option:\"([^\"]*)\"$")
    public void the_page_should_display_Categories_Option(String expOptions) throws Throwable {
        try {
            List<String> actOptions = new ArrayList<String>();
            ;
            List<WebElement> optionsToSelect = driver.findElement(By.xpath("//div[@class='list-group']")).findElements(By.tagName("a"));
            for (WebElement option : optionsToSelect) {
                String optionText = option.getText();
                actOptions.add(optionText);
            }
            List<String> expOptionsText = Arrays.asList(expOptions.trim().split(","));
            scen.write("Actual Options: " + actOptions);
            scen.write("Expected Options: " + actOptions);
            assertTrue(actOptions.containsAll(expOptionsText));
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String err = sw.toString();
            scen.write(err);
            throw new Exception();
        }
    }

    /**
     * Will verify wheather page is diplaying expected Tabs/Navigation tab options
     *
     * @param expTabs:Expected Tabs
     */
    @Then("^The page should display Navigation Option:\"([^\"]*)\"$")
    public void the_page_should_display_Navigation_Option(String expTabs) throws Throwable {
        try {
            List<String> actNavOptions = new ArrayList<String>();
            List<WebElement> navLists = Arrays.asList(hp.allProducts, hp.ourBrands, hp.myAccount, hp.newProducts, hp.ourServices);
            for (WebElement option : navLists) {
                String hours = option.getText();
                actNavOptions.add(hours);
            }
            List<String> expNavOptions = Arrays.asList(expTabs.trim().split(","));
            scen.write("Actual NavigationTabOptions: " + actNavOptions);
            scen.write("Expected NavigationTabOptions: " + expNavOptions);
            assertTrue(actNavOptions.equals(expNavOptions));
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String err = sw.toString();
            scen.write(err);
            throw new Exception();
        }
    }

    /**
     * Will search for expected item in the searchbox
     *
     * @param searchOptions:Expected Search Options
     */
    @When("^I search for an item:\"([^\"]*)\" in search box$")
    public void i_search_for_an_item_in_search_box(String searchOptions) throws Throwable {
        try {
            waitForElementLocator(driver, hp.searchTerm);
            hp.searchTerm.sendKeys(searchOptions);
            hp.searchButton.click();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String err = sw.toString();
            scen.write(err);
            throw new Exception();
        }
    }
}