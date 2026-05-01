package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.PracticeFormPage;
import pages.components.RegistrationResultComponent;
import tests.testData.FakerTestData;

public class TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    RegistrationResultComponent resultForm = new RegistrationResultComponent();
    FakerTestData fakerTestData = new FakerTestData();

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}
