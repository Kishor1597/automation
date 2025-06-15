package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.SignUpPage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SignUpSteps {

    WebDriver driver;
    SignUpPage signUpPage;
    LoginPage loginPage;

    public static String registeredEmail;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("I navigate to the Magento website")
    public void navigate_to_website() {
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
    }

    @When("I click on the {string} link")
    public void click_link(String linkText) {
        driver.findElement(org.openqa.selenium.By.linkText(linkText)).click();
    }

    @When("I fill in the registration form with valid details")
    public void fill_form() {
        signUpPage = new SignUpPage(driver);
        registeredEmail = "kishor" + System.currentTimeMillis() + "@test.com";
        signUpPage.fillForm("Kishor", "Kumar", registeredEmail, "Password@123");
    }

    @When("I submit the form")
    public void submit_form() {
        signUpPage.submitForm();
    }

    @Then("I should see a confirmation message")
    public void see_confirmation() throws Exception {
        Thread.sleep(3000);
        String actualMessage = driver.findElement(By.cssSelector("div.message-success.success.message")).getText();
        String expectedMessage = "Thank you for registering with Main Website Store.";
        captureScreenshot("signup_confirmation.png");
        Assert.assertTrue("Confirmation message is incorrect or not displayed",
                actualMessage.contains(expectedMessage));
    }

    @Given("I navigate to the Magento login page")
    public void navigate_to_login() {
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Sign In")).click();
        loginPage = new LoginPage(driver);
    }

    @When("I enter valid credentials")
    public void enter_credentials() {
        loginPage.login(registeredEmail, "Password@123");
    }

    @Then("I should be logged in successfully")
    public void login_successful() throws Exception {
        Thread.sleep(3000);
        String welcomeText = driver.findElement(By.cssSelector("div.panel.header > ul > li.greet.welcome")).getText();
        boolean isLoggedIn = welcomeText.contains("Welcome");
        captureScreenshot("login_success.png");
        Assert.assertTrue("Login failed or welcome message not found", isLoggedIn);
    }

    @When("I fill in the registration form with already registered email")
    public void fill_form_existing_email() {
        signUpPage = new SignUpPage(driver);
        signUpPage.fillForm("Kishor", "Kumar", registeredEmail, "Password@123");
    }

    @Then("I should see an account already exists error")
    public void verify_account_exists_error() throws Exception {
        Thread.sleep(2000);
        String errorText = driver.findElement(By.cssSelector("div.message-error.error.message")).getText();
        Assert.assertTrue("Error not shown", errorText.contains("There is already an account with this email address"));
        captureScreenshot("signup_existing_email.png");
    }

    @When("I enter invalid login credentials")
    public void enter_invalid_credentials() {
        loginPage = new LoginPage(driver);
        loginPage.login("invaliduser@test.com", "WrongPassword");
    }

    @Then("I should see an invalid login error")
    public void verify_invalid_login_error() throws Exception {
        Thread.sleep(2000);
        String errorText = driver.findElement(By.cssSelector("div.message-error.error.message")).getText();
        Assert.assertTrue("Login error not shown", errorText.contains("The account sign-in was incorrect"));
        captureScreenshot("invalid_login.png");
    }


    public void captureScreenshot(String fileName) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
