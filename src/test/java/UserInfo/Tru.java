package UserInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tru 
{

	public static WebElement before15days(WebDriver driver)
	{
		return driver.findElement(By.xpath("//*[@id='mCSB_2_container']/div[1]/div[5]/div[1]/div[2]/p/span"));
	}
	public static WebElement month(WebDriver driver)
	{
		//return driver.findElement(By.xpath("//*[@id='datepicker']/div/div/div/span[1]"));
		return driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[1]"));
	}
	public static WebElement year(WebDriver driver)
	{
		return driver.findElement(By.xpath("//*[@id='datepicker']/div/div/div/span[2]"));
	}
	
}
