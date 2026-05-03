package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    CalendarComponent calendar = new CalendarComponent();

    // Elements
    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput =  $("#lastName");
    private SelenideElement emailInput = $("#userEmail");
    private SelenideElement genderContainer = $("#genterWrapper");
    private SelenideElement phoneInput = $("#userNumber");
    private SelenideElement dateOfBirthdayInput = $("#dateOfBirthInput");
    private SelenideElement subjectsInput = $("#subjectsInput");
    private SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private SelenideElement stateSelect= $("#state");
    private SelenideElement citySelect= $("#city");
    private SelenideElement uploadPicture= $("#uploadPicture");
    private SelenideElement addressInput= $("#currentAddress");
    private SelenideElement submitButton = $("#submit");


    //Actions
    @Step("Открытие формы регистрации")
    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """);

        return this;
    }

    @Step("Вводим имя \"{valeu}\"")
    public PracticeFormPage typeFirstname(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Вводим фамилию \"{valeu}\"")
    public PracticeFormPage typeLastname(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Указываем почту \"{valeu}\"")
    public PracticeFormPage typeEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    @Step("Указываем гендер \"{valeu}\"")
    public PracticeFormPage setGender(String value) {
        genderContainer.$(byText(value)).click();

        return this;
    }

    @Step("Воодим телефон \"{valeu}\"")
    public PracticeFormPage typePhone(String value) {
        phoneInput.setValue(value);

        return this;
    }

    @Step("Указываем дату рождения {day} {month} {year}")
    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthdayInput.click();
        calendar.setDate(day, month, year);

        return this;
    }

    @Step("Выбираем тему {valeu}")
    public PracticeFormPage typeSubjects(String value) {
        subjectsInput
                .setValue(value)
                .pressEnter();

        return this;
    }

    @Step("Выбираем хобби {valeu}")
    public PracticeFormPage selectHobbies(String value) {
        $(byText(value)).click();

        return this;
    }

    @Step("Загружаем фото {valeu}")
    public PracticeFormPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath("Image/" + value);

        return this;
    }

    @Step("Скролим страницу")
    public PracticeFormPage scrollPage() {
        executeJavaScript("window.scrollBy(0, 500);");

        return this;
    }

    @Step("Вводим адрес дома {valeu}")
    public PracticeFormPage setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    @Step("Выбираем штат {valeu}")
    public PracticeFormPage setState(String value) {
        stateSelect.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    @Step("Выбираем город {valeu}")
    public PracticeFormPage setCity(String value) {
        citySelect.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    @Step("Отправляем форму")
    public PracticeFormPage submitButtonClick() {
        submitButton.click();

        return this;
    }
}
