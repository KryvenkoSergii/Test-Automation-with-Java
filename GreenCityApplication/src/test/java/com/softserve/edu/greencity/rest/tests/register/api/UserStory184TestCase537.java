package com.softserve.edu.greencity.rest.tests.register.api;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.softserve.edu.greencity.rest.data.User;
import com.softserve.edu.greencity.rest.data.UserRepository;
import com.softserve.edu.greencity.rest.data.jira.testcases.GC535Data;
import com.softserve.edu.greencity.rest.data.jira.testcases.GC536Data;
import com.softserve.edu.greencity.rest.data.jira.testcases.GC537Data;
import com.softserve.edu.greencity.rest.tests.GreencityRestTestRunner;
import com.softserve.edu.greencity.ui.data.Languages;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

/**
 * UserStory184TestCase537 class. Negative test of registration user with bad
 * credentials. (Jira Story: SC-184/GC-468; Test GC-537)
 * Verify that user is not registered with too short password via /ownSecurity/signUp call.
 * @author SergiiK
 * 2020-06-02
 */
public class UserStory184TestCase537 extends GreencityRestTestRunner {

    /**
     * Bad user credentials (Jira Story: SC-184/GC-468; Test GC-537) email
     * "qtiguoku@ttirv.net";name "Sergii_Test536"password invalid "2Ab_".
     * @return
     */
    @DataProvider
    public Object[][] badUserCredentials() {
        return new Object[][] { { UserRepository.get().userForUserStory184TestCase537(), Languages.ENGLISH }, };
    }

    /**
     * Negative test Verify that the user cannot register with bad password.
     * @param user's credentials.
     */
    @Description("Negative test Verify that the user cannot register with bad password.")
    @Issue("GC-537")
    @Story("Verify that user is not registered with too short password via /ownSecurity/signUp call.")
    @Step("5. Check response (code, name and message)")
    @Parameters({"User credentials", "languages"})
    @Test(dataProvider = "badUserCredentials")
    public void checkUserRegistrationWithBadPassword(User badUserCredentials, Languages languages) {
        logger.info("Jira Story: SC-184/GC-468; Test GC-537");
        logger.info("Verify that user is not registered with too short password via /ownSecurity/signUp call.");
        logger.info("start test checkUserRegistrationWithBadPassword with user = " + badUserCredentials.toString());
        //
        logger.info("REST: register a user with bad credentials");
        GC537Data unsuccessfulRegistration = loadApplication().userStory184Test537(languages, badUserCredentials);
        Assert.assertEquals(unsuccessfulRegistration.getName(), GC537Data.EXPECTED_NAME, "expected name isn't match");
        Assert.assertEquals(unsuccessfulRegistration.getMessage(), GC537Data.EXPECTED_MESSAGE, "expected message isn't match");
    }

}
