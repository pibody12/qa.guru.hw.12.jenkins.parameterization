package tests;

import org.junit.jupiter.api.Test;

import static utils.RandomUtils.*;

public class PracticeFormTests extends TestBase {

    void fillFullTest() {
        String randomBirthdayDay = String.valueOf(fakerTestData.fakerBirthday);
        String randomBirthdayYear = String.valueOf(fakerTestData.fakerYear);
        String randomBirthdayDate = randomBirthdayDay + " " + fakerTestData.fakerMonth + "," + randomBirthdayYear;
        String randomState = getRandomState();
        String randomCity = selectCity(randomState);
        String randomHobbies = selectHobbies();

        practiceFormPage.openPage()
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

        // Проверка формы и заполненых полей
        resultForm.checkModalForm()
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
    }
}