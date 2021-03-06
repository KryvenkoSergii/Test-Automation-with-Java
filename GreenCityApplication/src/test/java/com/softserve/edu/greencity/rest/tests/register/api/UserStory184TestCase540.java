package com.softserve.edu.greencity.rest.tests.register.api;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.softserve.edu.greencity.rest.data.User;
import com.softserve.edu.greencity.rest.data.UserRepository;
import com.softserve.edu.greencity.rest.data.jira.testcases.GC540Data;
import com.softserve.edu.greencity.rest.tests.GreencityRestTestRunner;
import com.softserve.edu.greencity.ui.data.Languages;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

/**
 * UserStory184TestCase540 class. Negative test of registration user with bad
 * credentials. (Jira Story: SC-184/GC-468; Test GC-540)
 * Verify that user is not registered with too long name via /ownSecurity/signUp call.
 * @author SergiiK
 * 2020-06-02
 */
public class UserStory184TestCase540 extends GreencityRestTestRunner {

    /**
     * Bad user credentials (Jira Story: SC-184/GC-468; Test GC-540)email valid
     * random 20 symbols;name invalid random 27 symbols;password valid "Aasdas_123".
     * @return
     */
    @DataProvider
    public Object[][] badUserCredentials() {
        return new Object[][] { { UserRepository.get().userForUserStory184TestCase540(), Languages.ENGLISH }, };
    }

    /**
     * Negative test Verify that the user cannot register with bad password.
     * @param user's credentials.
     */
    @Description("Negative test Verify that the user cannot register with bad password.")
    @Issue("GC-540")
    @Story("Verify that user is not registered with too long name via /ownSecurity/signUp call.")
    @Step("5. Check response (code, name and message)")
    @Parameters({"User credentials", "languages"})
    @Test(dataProvider = "badUserCredentials")
    public void checkUserRegistrationWithBadName(User badUserCredentials, Languages languages) {
        logger.info("Jira Story: SC-184/GC-468; Test GC-540");
        logger.info("Verify that user is not registered with too long name via /ownSecurity/signUp call.");
        logger.info("start test checkUserRegistrationWithBadName with user = " + badUserCredentials.toString());
        //
        logger.info("REST: register a user with bad credentials");
        GC540Data unsuccessfulRegistration = loadApplication().userStory184Test540(languages, badUserCredentials);
        Assert.assertEquals(unsuccessfulRegistration.getName(), GC540Data.EXPECTED_NAME, "expected name isn't match");
        Assert.assertEquals(unsuccessfulRegistration.getMessage(), GC540Data.EXPECTED_MESSAGE, "expected message isn't match");
    }

}
