package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class PracticeFormTests extends TestBase {

    @Test
    @DisplayName("Проверка формы регистрации")
    void fillFullTest() {

        step("Открываем страницу с регистрацией", () -> {
                    practiceFormPage.openPage();
                });

        step("Заполняем форму", () -> {
                    practiceFormPage
                            .typeFirstname(fakerTestData.fakerUserFirstName)
                            .typeLastname(fakerTestData.fakerUserLastName)
                            .typeEmail(fakerTestData.fakerUserEmail)
                            .setGender(fakerTestData.fakerGender)
                            .typePhone(fakerTestData.fakerUserPhoneNumber)
                            .setDateOfBirth(randomBirthdayDay, fakerTestData.fakerMonth, randomBirthdayYear)
                            .typeSubjects(fakerTestData.fakerSubject)
                            .selectHobbies(randomHobbies)
                            .uploadPicture("photo.jpg")
                            .scrollPage()
                            .setAddress(fakerTestData.fakerUserAddress)
                            .setState(randomState)
                            .setCity(randomCity)
                            .submitButtonClick();
                });

        step("Проверка наличия формы", () -> {
                    resultForm.checkModalForm();
                });

        step("Проверка результата регистрации", () -> {
            resultForm
                    .checkKeyValue("Student Name", fakerTestData.fakerUserFirstName + " " + fakerTestData.fakerUserLastName)
                    .checkKeyValue("Student Email", fakerTestData.fakerUserEmail)
                    .checkKeyValue("Gender", fakerTestData.fakerGender)
                    .checkKeyValue("Mobile", fakerTestData.fakerUserPhoneNumber)
                    .checkKeyValue("Date of Birth", randomBirthdayDate)
                    .checkKeyValue("Hobbies", randomHobbies)
                    .checkKeyValue("Subjects", fakerTestData.fakerSubject)
                    .checkKeyValue("Picture", "photo.jpg")
                    .checkKeyValue("Address", fakerTestData.fakerUserAddress)
                    .checkKeyValue("State and City", randomState + " " + randomCity);
        });
    }
}