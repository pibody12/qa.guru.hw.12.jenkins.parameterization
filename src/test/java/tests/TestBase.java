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
    static String browserVersion = System.getProperty("browserVersion", "128.0");
    static String browserSize = System.getProperty("browserSize", "1920x1080");
    static String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
    static String enableRecordVideo = System.getProperty("enableRecordVideo", "true");
    static String userSelenoid = System.getProperty("userSelenoid");
    static String urlSelenoid = System.getProperty("urlSelenoid", "");
    static String urlVideo = System.getProperty("urlVideo", "selenoid.autotests.cloud/wd/hub");

//    ДЗ
//1. разные типы браузера +
//2. разные версии браузера +
//3. передаль через параметр урл селенойда причем логин/пас отдельно путь отдельно +
//4. передать через параметр адрес сайта +
//5. разрешение +
//6. урл видео тоже передать в параметры

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = browserSize;
        Configuration.baseUrl = baseUrl;
        Configuration.pageLoadStrategy = "eager";


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", enableRecordVideo
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://" + userSelenoid + "@" + urlSelenoid;
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
        Attach.addVideo(urlVideo);

        Selenide.closeWebDriver();
    }
}
