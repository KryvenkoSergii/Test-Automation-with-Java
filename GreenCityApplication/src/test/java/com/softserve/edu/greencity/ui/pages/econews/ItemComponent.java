package com.softserve.edu.greencity.ui.pages.econews;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ItemComponent {
	
	private WebDriver driver;
	private WebElement newsItem;
	private List<WebElement> tags;
	private WebElement title;
	private WebElement content;
	private WebElement dateOfCreation;
	private WebElement author;
	

	public ItemComponent(WebDriver driver, WebElement newsItem) {
	
		this.driver = driver;
		this.newsItem = newsItem;
		initElements();
	}
	
	private void initElements() {
		
		tags = newsItem.findElements(By.cssSelector("div.filter-tag div"));
		title = newsItem.findElement(By.cssSelector("div.title-list p"));
		content = newsItem.findElement(By.cssSelector("div.list-text p"));
		dateOfCreation = newsItem.findElement(By.cssSelector("div.user-data-added-news > p:nth-child(1)"));
		author = newsItem.findElement(By.cssSelector("div.user-data-added-news > p:nth-child(2)"));
	}

	// Page Object
	
	//typeOfNews
	
	public List<WebElement> getTags() {
		return tags;
	}
	
	public List<String> getTagsText() {
		List<String> str = new ArrayList<String>();
		for(WebElement elem : getTags() ) {
			str.add(elem.getText().toLowerCase());
		}
		return  str;
	}

	//container
	
	public WebElement getNewsItem() {
		return newsItem;
	}

	//header
	
	public WebElement getIitle() {
		return title;
	}

	public String getIitleText() {
		return getIitle().getText();
	}

	public void clickIitle() {
		getIitle().click();
	}

	// content
	
	public WebElement getContent() {
		return content;
	}

	public String getContentText() {
		return getContent().getText();
	}
	
	public void clickContent() {
		getContent().click();
	}
	
	// dateOfCreation
	
	public WebElement getDateOfCreation() {
		return dateOfCreation;
	}

	public String getDateOfCreationText() {
		return getDateOfCreation().getText();
	}

	//author
	
	public WebElement getAuthor() {
		return author;
	}

	public String getAuthorText() {
		return getAuthor().getText();
	}


	// Functional

	// Business Logic
}
