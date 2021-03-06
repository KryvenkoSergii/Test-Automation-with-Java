package com.softserve.edu.greencity.ui.pages.econews;

import com.softserve.edu.greencity.ui.data.NewsData;
import com.softserve.edu.greencity.ui.pages.common.TopPart;
import com.softserve.edu.greencity.ui.tools.UploadFileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

/**
 * @author lv-493 Taqc/Java
 */
public class CreateNewsPage extends TopPart {

    private TagsComponent tagsComponent;

    private WebElement titleField;
    private WebElement sourceField;
    private WebElement contentField;
    private WebElement dateField;
    private WebElement authorField;
    private WebElement cancelButton;
    private WebElement previewButton;
    private WebElement publishButton;
    private WebElement dropArea;

    //description fields
    private WebElement titleDescription;
    private WebElement tagsDescription;
    private WebElement sourceDescription;
    private WebElement contentDescription;
    private WebElement pictureDescription;

    /**
     * @param driver
     */
    public CreateNewsPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        // init main elements
        tagsComponent = new TagsComponent(driver);

        titleField = driver.findElement(By.cssSelector("input[formcontrolname='title']"));
        sourceField = driver.findElement(By.cssSelector("div[formarrayname='tags']+label > input"));
        contentField = driver.findElement(By.cssSelector("div.textarea-wrapper > textarea"));
        dateField = driver.findElement(By.cssSelector("div.date > p:first-child > span"));
        authorField = driver.findElement(By.cssSelector("div.date > :nth-child(2n) > span"));
        dropArea = driver.findElement(By.cssSelector("div.text-wrapper"));
        cancelButton = driver.findElement(By.cssSelector("div.submit-buttons > :first-child"));
        previewButton = driver.findElement(By.cssSelector("div.submit-buttons > :first-child+button"));
        publishButton = driver.findElement(By.cssSelector("div.submit-buttons > button[type='submit']"));

        // init description fields
        titleDescription = driver.findElement(By.cssSelector("input[formcontrolname='title'] + span"));
        tagsDescription = driver.findElement(By.cssSelector("div.tags > button + p"));
        sourceDescription = driver.findElement(By.cssSelector("div[formarrayname='tags']+label > input + span"));
        contentDescription = driver.findElement(By.cssSelector("p.textarea-description"));
        pictureDescription = driver.findElement(By.xpath("//div[@class = 'text-wrapper']/../../div/../span"));
    }

    // Page Object

    //titleField
    public WebElement getTitleField() {
        return titleField;
    }

    public void setTitleField(String text) {
        getTitleField().sendKeys(text);
    }

    public String getTitleFieldText() {
        return getTitleField().getText();
    }

    public void clearTitleField() {
        getTitleField().clear();
    }

    public void clickTitleField() {
        getTitleField().click();
    }

    //sourceField
    public WebElement getSourceField() {
        return sourceField;
    }

    public void setSourceField(String text) {
        getSourceField().sendKeys(text);
    }

    public String getSourceFieldText() {
        return getSourceField().getText();
    }

    public void clearSourceField() {
        getSourceField().clear();
    }

    public void clickSourceField() {
        getSourceField().click();
    }

    //contentField

    public WebElement getContentField() {
        return contentField;
    }

    public void setContentField(String text) {
        getContentField().sendKeys(text);
    }

    public String getContentFieldText() {
        return getContentField().getText();
    }

    public void clearContentField() {
        getContentField().clear();
    }

    public void clickContentField() {
        getContentField().click();
    }

    //dateField
    public WebElement getDateField() {
        return dateField;
    }

    public String getDateFieldText() {
        return getDateField().getText();
    }

    //authorField
    public WebElement getAuthorField() {
        return authorField;
    }

    public String getAuthorFieldText() {
        return getAuthorField().getText();
    }

    //cancelButton;
    public WebElement getCancelButton() {
        return cancelButton;
    }

    public void clickCancelButton() {
        getCancelButton().click();
    }

    //previewButton

    public WebElement getPreviewButton() {
        return previewButton;
    }

    public void clickPreviewButton() {
        getPreviewButton().click();
    }

    //publishButton

    public WebElement getPublishButton() {
        return publishButton;
    }

    public void clickPublishButton() {
        getPublishButton().click();
    }

    // titleDescription and warning
    public WebElement getTitleDescription() {
        return titleDescription;
    }

    public boolean isTitleDescriptionWarning() {
        return getTitleField().getAttribute("class").contains("invalid");
    }

    // sourceDescription and warning
    public WebElement getSourceDescription() {
        return sourceDescription;
    }

    public boolean isSourceDescriptionWarning() {
        return getSourceDescription().getAttribute("class").contains("warning");
    }

    // contentDescription and warning
    public WebElement getContentDescription() {
        return contentDescription;
    }

    public boolean isContentDescriptionWarning() {
        return getContentField().getAttribute("class").contains("invalid");
    }

    // pictureDescription and warning
    public WebElement getPictureDescription() {
        return pictureDescription;
    }

    public boolean isPictureDescriptionWarning() {
        return getPictureDescription().getAttribute("class").contains("warning-color");
    }

    // tagsDescription and warning
    public WebElement getTagsDescription() {
        return tagsDescription;
    }

    public boolean isTagsDescriptionWarning() {
        return getTagsDescription().getAttribute("class").contains("warning");
    }

    // Functional

    // Drop and upload file
    private CreateNewsPage uploadFile(WebElement dropArea, String path) {
        String absolutePath = new File(path).getAbsolutePath();
        UploadFileUtils.DropFile(new File(absolutePath), dropArea, 0, 0);
        return this;
    }

    /**
     * Method to fill all field in CreateNewsPage
     *
     * @param newsData
     */
    public CreateNewsPage fillAllNewsFields(NewsData newsData) {
        tagsComponent.selectTags(newsData.getTags());
        clearTitleField();
        setTitleField(newsData.getTitle());
        clearContentField();
        setContentField(newsData.getContent());
        clearSourceField();
        setSourceField(newsData.getSource());
        uploadFile(dropArea, newsData.getFilePath());
        return this;
    }

    public CreateNewsPage fillRequiredFields(NewsData newsData) {
        tagsComponent.selectTags(newsData.getTags());
        clearTitleField();
        setTitleField(newsData.getTitle());
        clearContentField();
        setContentField(newsData.getContent());
        return this;
    }

    // Business Logic

    /**
     * Method to open PreViewPage
     *
     * @return PreViewPage
     */
    public PreViewPage goToPreViewPage() {
        clickPreviewButton();
        return new PreViewPage(driver);
    }

    /**
     * Method to Publish news
     *
     * @return EconewsPage
     */
    public EconewsPage publishNews() {
        clickPublishButton(); // Button doesn't work
        return new EconewsPage(driver);
    }

    /**
     * Method to cancel news creation
     * by clicking Cancel button in IFrame
     *
     * @return EconewsPage
     */
    public EconewsPage cancelNewsCreating() {
        clickCancelButton();
        IFrame iFrame = new IFrame(driver);
        iFrame.getCancelEditingButton().click();
        iFrame = null;
        return new EconewsPage(driver);
    }

    /**
     * Method to continue news creation after clicking Cancel button
     * by clicking ContinueEditing button in IFrame
     *
     * @return CreateNewsPage
     */
    public CreateNewsPage continueNewsCreating() {
        clickCancelButton();
        IFrame iFrame = new IFrame(driver);
        iFrame.getContinueEditingButton().click();
        iFrame = null;
        return this;
    }

    //inner class
    private class IFrame { //static?

        private WebElement continueEditingButton;
        private WebElement cancelEditingButton;

        public IFrame(WebDriver driver) {
            initElements(driver);
        }

        public WebElement getContinueEditingButton() {
            return continueEditingButton;
        }

        public WebElement getCancelEditingButton() {
            return cancelEditingButton;
        }

        public void initElements(WebDriver driver) {
            continueEditingButton = driver.findElement(By.cssSelector("div.continue-btn > button"));
            cancelEditingButton = driver.findElement(By.cssSelector("button.primary-global-button"));
        }
    }

}