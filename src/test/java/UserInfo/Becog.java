package UserInfo;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
public class Becog 
{
	public static void getdriver(WebDriver driver)
	{
		driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
	}
	public static WebElement userinformation(WebDriver driver) 
	{
		return driver.findElement(By.xpath("//*[@id='O365_MainLink_MePhoto']/div/div/div/div/div[2]"));
	}
	public static WebElement username(WebDriver driver)
	{
		WebElement username=driver.findElement(By.id("mectrl_currentAccount_primary"));;
		return username;
	}
	public static WebElement email(WebDriver driver)
	{
		WebElement email=driver.findElement(By.xpath("//div[@id='mectrl_currentAccount_secondary']"));
		return email;
	}
//	public static WebElement sdown(WebDriver driver)
//	{
//		//WebElement scroll=driver.findElement(By.xpath("//*[@id='QuicklinksItemTitle']"));
//		WebElement scroll=driver.findElement(By.xpath("//*[@id=\"89c5ffca-2ffb-4052-a723-e99c8c9a14ef\"]"));
//
//		return scroll;
//	}
	public static WebElement onec(WebDriver driver)
	{
		WebElement r=driver.findElement(By.xpath("//div[contains(text(),'OneCognizant')]"));
		return r;
	}
}