package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.HashMap;

/**
 * "HomePage.java" class file handles all the relevant HomePage Element Locators for automation framework
 * This Page is demonstration of Page Object Model of the framework
 *
 * @author shan.reddy
 */
public class HomePage {

    final WebDriver driver;
    public By elementLocator;
    //Search Term Locator on HomePage
    @FindBy(how = How.XPATH, using = "//input[@id='searchTerm']")
    public WebElement searchTerm;

    //"Search Button" Locator on HomePage
    @FindBy(how = How.XPATH, using = "//input[@id='btnSearch']")
    public WebElement searchButton;

    //RS LOGO Locator on HomePage
    @FindBy(how = How.XPATH, using = "//i[@class='icon-rslogo']")
    public WebElement rsLogo;

    //"All Products" Locator on HomePage
    @FindBy(how = How.XPATH, using = "//a[@title='All Products']")
    public WebElement allProducts;

    //"Our Brands" Locator on HomePage
    @FindBy(how = How.XPATH, using = "//a[@title='Our Brands']")
    public WebElement ourBrands;

    //"My Account" Locator on HomePage
    @FindBy(how = How.XPATH, using = "//a[@title='My Account']")
    public WebElement myAccount;

    //"New Products" Locator on HomePage
    @FindBy(how = How.XPATH, using = "//a[@title='New Products']")
    public WebElement newProducts;

    //"Our Services" Locator on HomePage
    @FindBy(how = How.XPATH, using = "//a[@title='Our Services']")
    public WebElement ourServices;


    public By homePageLocs(String locs) throws Exception {
        HashMap<String, By> hmap = new HashMap();
        /*Adding elements to HashMap*/
        try {
            //"Log In" link locator
            hmap.put("LogIn", By.xpath("//a[@href='https://uk.rs-online.com/web/authHome.html']"));
            //"Register" link locator
            hmap.put("Register", By.xpath("//a[@href='/web/register/registration']"));

            //Headers Text locators
            hmap.put("Categories", By.xpath("//h3[contains(text(),'Categories')]"));
            hmap.put("NewProducts", By.xpath("//h2[@class='heading-border-bold-btm'][contains(text(),'New Products')]"));
            hmap.put("FeaturedBrands", By.xpath("//h2[@class='heading-border-bold-btm'][contains(text(),'Featured Brands')]"));
            hmap.put("Applications&Solutions", By.xpath("//h2[@class='heading-border-bold-btm'][contains(text(),'Applications & Solutions')]"));
            elementLocator = hmap.get(locs);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return elementLocator;
    }

    // This is a constructor, as every page need a base driver to find web elements
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
}

