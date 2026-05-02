package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.PracticeFormPage;
import pages.components.RegistrationResultComponent;
import tests.testData.FakerTestData;

import java.util.Map;

import static utils.RandomUtils.*;

public class TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    RegistrationResultComponent resultForm = new RegistrationResultComponent();
    FakerTestData fakerTestData = new FakerTestData();

    String randomBirthdayDay = String.valueOf(fakerTestData.fakerBirthday);
    String randomBirthdayYear = String.valueOf(fakerTestData.fakerYear);
    String randomBirthdayDate = randomBirthdayDay + " " + fakerTestData.fakerMonth + "," + randomBirthdayYear;
    String randomState = getRandomState();
    String randomCity = selectCity(randomState);
    String randomHobbies = selectHobbies();

    static String browser = System.getProperty("browser", "chrome");

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = browser;
//        Configuration.browserVersion = "128.0";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @BeforeEach
    public void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        Selenide.closeWebDriver();
    }
}
