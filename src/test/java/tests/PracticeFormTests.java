package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
    }

    @Test
    void successfulFillPracticeFormTest() throws InterruptedException {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String email = "ivan.ivanov@company.com";
        String gender = "Male";
        String mobilePhone = "1234567890";
        String monthOfBirth = "January";
        String yearOfBirth = "1990";
        String dayOfBirth = "1";
        String[] subjects = new String[]{"Maths", "English", "Computer Science"};
        String[] hobbies = new String[]{"Reading", "Music"};
        String picturePath = "src/test/resources/img.jpg";
        String pictureName = "img.jpg";
        String currentAddress = "Rajasthan, Jaipur, Street, 1st";
        String state = "Rajasthan";
        String city = "Jaipur";

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(String.format("input[value='%s'", gender)).parent().$("label").click();
        $("#userNumber").setValue(mobilePhone);

        //  Select date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(monthOfBirth);
        $(".react-datepicker__year-select").selectOptionContainingText(yearOfBirth);
        $(String.format("[aria-label='Choose Monday, %s %sst, %s']", monthOfBirth, dayOfBirth, yearOfBirth)).click();

        //  Select subjects
        for (String subject : subjects) {
            $("#subjectsInput").setValue(subject).pressEnter();
        }

        //  Select hobbies
        for (String hobby : hobbies) {
            $("#hobbiesWrapper").$(byText(hobby)).click();
        }

        // Upload picture
        File picture = new File(picturePath);
        $("#uploadPicture").uploadFile(picture);

        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();

        // Click submit button
        $("#submit").click();

        // Assertions
        $(byText("Student Name")).parent().shouldHave(text(firstName), text(lastName));
        $(byText("Student Email")).parent().shouldHave(text(email));
        $(byText("Gender")).parent().shouldHave(text(gender));
        $(byText("State and City")).parent().shouldHave(text(state), text(city));

        $x("//td[text()='Mobile']//..").shouldHave(text(mobilePhone));
//        $(byText("Mobile")).parent().shouldHave(text(mobilePhone));


        $x("//td[text()='Date of Birth']//..").shouldHave(text(dayOfBirth), text(monthOfBirth), text(yearOfBirth));
        $(byText("Date of Birth")).parent().shouldHave(text(dayOfBirth), text(monthOfBirth), text(yearOfBirth));
//        $(byText("Mobile")).parent().shouldHave(1234567890));


//        $(byText("Date of Birth")).parent().shouldHave(text(String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth)));
//        $(byText("Date of Birth")).parent().shouldHave(text(monthOfBirth));
//        $(byText("Subjects")).parent().shouldHave(text("Maths"));

//        $(byText("Picture")).parent().shouldHave(text(pictureName));

//        sleep(10000);
        System.out.println("debug");
    }
}
