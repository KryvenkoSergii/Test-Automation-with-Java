package sergii.kryvenko.selenium.testng;

import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStackConfiguration {

    private String browserStackUsername = System
            .getenv("BROWSERSTACK_USERNAME");
    private String browserStackAccess_key = System
            .getenv("BROWSERSTACK_ACCESS_KEY");
    private String URL = "https://" + browserStackUsername + ":"
            + browserStackAccess_key + "@hub-cloud.browserstack.com/wd/hub";

    public BrowserStackConfiguration() {
    }

    public DesiredCapabilities getBrowserStackCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "80.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1024x768");
        caps.setCapability("name", "Bstack-[Java] Open Cart Test_14.04.20");
        caps.setCapability("browserstack.local", "true");
        return caps;
    }

}
