package com.tassta.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextBoxTests {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;               // To leave browser open after running the tests.
        Configuration.baseUrl = "https://demoqa.com";       // Entering baseUrl of the website.
        Configuration.browserSize = "1920x1080";            // Specifying the window resolution.
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");  // Specifying the page on baseUrl website.
        // Test data variables.
        String firstName = "Nikita";            // First name.
        String lastName = "Kirpu";              // Last name.
        String gender = "Male";                 // Gender.
        String emailAddress = "nk@tassta.com";  // Email address.
        String phoneNumber = "9995353598";      // Phone number.
        String birthMonth = "August";           // Month of birth.
        String birthYear = "1999";              // Year of birth.
        String birthDay = "14";                 // Day of birth.
        String state = "Haryana";               // State.
        String city = "Karnal";                 // City.
        String address = "Meera Ghati Park, Sector 14, Karnal, Haryana 132001"; // Address.
        String picture_1 = "img1.png";          // Image filename.
        String picturePath = "test/img/";       // Image path from source root. (Set the demoqa-tests-12 to source root)
        String[] hobby = new String[]{"Music", "Reading", "Sports"}; //Creating an Array for hobbies.
        String[] subjects = new String[]{"Biology", "Chemistry"};    //Creating an Array for subjects.

        // Entering test data.
        $("[id=close-fixedban]").click();                               // Closing the ad banner.
        $("[id=firstName]").setValue(firstName);                        // Entering the first name.
        $("[id=lastName]").setValue(lastName);                          // Entering the last name.
        $("[id=userEmail]").setValue(emailAddress);                     // Entering the email address.
        $(byText(gender)).click();                                               // Selecting the gender.
        $("[id=userNumber]").setValue(phoneNumber);                     // Entering  the phone number.
        $("[id=dateOfBirthInput]").click();                             // Entering the date of birth.
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__year-select").selectOption(birthYear);
        $("[aria-label$='" + birthMonth + " " + birthDay + "th, " + birthYear + "']").click();
        $("[id=subjectsInput]").setValue(subjects[0]).pressEnter();       // Entering subjects.
        $("[id=subjectsInput]").setValue(subjects[1]).pressEnter();
        $(byText(hobby[0])).click();                                               // Entering  hobbies.
        $(byText(hobby[1])).click();
        $("[id=uploadPicture]").uploadFromClasspath(picturePath + picture_1); // Uploading the picture.
        $("[id=currentAddress]").setValue(address); // Entering the address.
        $("[id=state]").scrollIntoView(true).click();                    // Selecting state and city from the dropdown.
        $(byText(state)).click();
        $("[id=city]").click();
        $(byText(city)).click();
        $("[id=submit]").shouldBe(visible).click();                      // Submitting the data.

        // Asserts
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form")); // Checking the header of the response form.
        var responseDivided = $(".modal-body").toString().split("\n");              // Dividing the response by lines.
        assertEquals(responseDivided[1], ("Student Name " + firstName + " " + lastName));                 // Checking First and Last names.
        assertEquals(responseDivided[2], ("Student Email " + emailAddress));                              // Checking email address.
        assertEquals(responseDivided[3], ("Gender " + gender));                                           // Checking gender.
        assertEquals(responseDivided[4], ("Mobile " + phoneNumber));                                      // Checking phone number.
        assertEquals(responseDivided[5], ("Date of Birth " + birthDay + " " + birthMonth + "," + birthYear)); // Checking birth date.
        assertEquals(responseDivided[6], ("Subjects " + subjects[0] + ", " + subjects[1]));               // Checking subjects.
        assertEquals(responseDivided[7], ("Hobbies " + hobby[0] + ", " + hobby[1]));                      // Checking hobbies.
        assertEquals(responseDivided[8], ("Picture " + picture_1));                                       // Checking image.
        assertEquals(responseDivided[9], ("Address " + address));                                         // Checking address.
        assertEquals(responseDivided[10], ("State and City " + state + " " + city + "</div>"));           // Checking state and city.
    }
}
