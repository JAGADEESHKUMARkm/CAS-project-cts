package UserInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Onecog 
{
	public static WebElement search (WebDriver driver)
	{
		return driver.findElement(By.xpath("//*[@id='oneC_searchAutoComplete']"));
	}
	public static WebElement tticon(WebDriver driver)
	{
		return driver.findElement(By.xpath("//*[@id='newSearchAppsLST']/div/div/div[2]"));
	}
	public static WebElement sdowns(WebDriver driver)
	{
		return driver.findElement(By.xpath("//*[@id='mCSB_2_container']/div[2]/div[4]"));
	}

}
