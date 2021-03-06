package com.softserve.edu.greencity.ui.pages.econews;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.greencity.ui.data.NewsData;

public class ItemsContainer {
	
	private WebDriver driver;
	private List<ItemComponent> itemComponents;
	
	public ItemsContainer(WebDriver driver) {
		this.driver = driver;
		initElements();
	}

	private void initElements() {
		
		itemComponents = new ArrayList<>();
		 for (WebElement current : driver
				 .findElements(By.xpath("//div[@class=\"container\"]//ul[contains(@class, \"list\")]/li"))) {
			 itemComponents.add(new ItemComponent(driver, current));
	        }
	}
	
	// Page Object
	
    //itemComponents
	
    public List<ItemComponent> getItemComponents() {
    	return itemComponents;
    }

	// Functional
    
    /**
     * In this method we get how many ItemComponent is displayed
     * @return int 
     */
    public int getItemComponentsCount(){
        return getItemComponents().size();
    }
    
    /**
     * We get list of all news headers 
     * @return list itemComponentsHeader
     */
    public List<String> getItemComponentsHeader() {  
        List<String> itemComponentsHeader = new ArrayList<>();
        for(ItemComponent cur : getItemComponents()) {
        	itemComponentsHeader.add(cur.getIitleText());
        }
        return itemComponentsHeader;
    }
    
    /**
     * Find news by header
     * @param  String headerName
     * @return ItemComponent
     */
    public ItemComponent getItemComponentByHeader(String headerName){
    	ItemComponent result = null;
        for (ItemComponent cur : getItemComponents()) {
            if (cur.getIitleText().toLowerCase()
                    .contains(headerName.toLowerCase())) {
                result = cur;
                break;
            }
        }
        if (result == null) {
        	System.err.format(headerName, result);
            //throw new RuntimeException("HeaderName " + headerName + " not found");
        }
        return result;
    }
    
    /**
     * Open new page with news given by title, tags, content
     * @param OneNewsData one
     */
    public void clickItemComponentOpenPage(NewsData news) {
//   	getItemComponentByHeader(news.getTitle()).clickContent();
    	findItemComponentByParameters(news).getIitle().click();
    	
    }
    
    
    /**
     * Find appropriate news by its parameters: title, list tags & content
     * @param  OneNewsData 
     * @return ItemComponent
     */
    public ItemComponent findItemComponentByParameters(NewsData news) {
    	ItemComponent result = null;
    	for(ItemComponent cur : getItemComponents() ) {
    		if(cur.getIitleText().toLowerCase().equals(news.getTitle().toLowerCase()) 
    				&& cur.getTagsText().equals(news.getTagsName())
    				&& news.getContent().toLowerCase().contains(cur.getContentText().toLowerCase())) {
    					result = cur;
    		}
    	}
    	if (result == null) {
         	System.err.format("no matches with ", news.toString());
             //throw new RuntimeException("ItemComponent with parameters " + one.toString() + " not found");
         }
		return result;	
    }
    
    /**
     * this method return ItemComponent by its number
     * @param number
     * @return ItemComponent
     */
 	public ItemComponent chooseNewsByNumber(int number) {
 				return getItemComponents().get(number);
	}
    
	// Business Logic
 	}
