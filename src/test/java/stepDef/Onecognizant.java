package stepDef;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import UserInfo.Becog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Onecognizant 
{
	public WebDriver driver;
	private static List<String> dates = new ArrayList<String>();
	@Given("user is navigating to Be.Cognizant website")
	public void user_is_navigating_to_be_cognizant_website() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver = new ChromeDriver();
		driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
		Thread.sleep(20000);
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	@Then("the user gets verified")
	public void the_user_gets_verified() throws InterruptedException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		WebElement user=driver.findElement(By.xpath("//*[@id='O365_MainLink_MePhoto']/div/div/div/div/div[2]"));
		Thread.sleep(3000);
		user.click();
		Thread.sleep(3000);
		String name=driver.findElement(By.id("mectrl_currentAccount_primary")).getText();
		String email=driver.findElement(By.id("mectrl_currentAccount_secondary")).getText();
		System.out.println(name);
		System.out.println(email);
		FileInputStream f = new FileInputStream(System.getProperty("user.dir")+"\\ExcelSheet\\Ex02.xlsx");
		XSSFWorkbook wbook = new XSSFWorkbook(f);
		XSSFSheet sh = wbook.getSheetAt(0);
		XSSFRow rows = sh.getRow(0);
		//value stored in a string from excel
		String username = rows.getCell(0).getStringCellValue();	
		String emailid = rows.getCell(1).getStringCellValue();
		if(name.equals(username)&& email.equals(emailid))
		{
			System.out.println("User Information is verified");
		}
		else
		{
			System.out.println("User Information is Not verified");
		}
	}
	    
	@When("One Cognizant is present")
	public void one_cognizant_is_present() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		WebElement oneC=driver.findElement(By.xpath("//*[@id='CaptionElementView']"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", oneC);
		Thread.sleep(8000);
		WebElement element =driver.findElement(By.xpath("//*[@id='vpc_WebPart.QuickLinksWebPart.internal.89c5ffca-2ffb-4052-a723-e99c8c9a14ef']/div/div/div/div[2]/div/div/div/div/div/div/div/div[1]/div[1]/div/div/div/a/div"));;
		if(element!=null)
			System.out.println("OneCognizant is Present");
		else
			System.out.println("OneCognizant is Absent");
		
	    
	}
	@Then("One Cognizant should be clicked")
	public void one_cognizant_should_be_clicked() throws InterruptedException {
	    
		driver.findElement(By.xpath("//*[@id='vpc_WebPart.QuickLinksWebPart.internal.89c5ffca-2ffb-4052-a723-e99c8c9a14ef']/div/div/div/div[2]/div/div/div/div/div/div/div/div[1]/div[1]/div/div/div/a/div")).click();
	}	    
	
	@When("Trutime is typed on the search bar")
	public void trutime_is_typed_on_the_search_bar() throws InterruptedException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		String oldwindow=driver.getWindowHandle();
		Set<String>handles=driver.getWindowHandles();
		for (String newwindow : handles) 
		{
			driver.switchTo().window(newwindow);
		}
		Thread.sleep(4000);
		//searching trutime and selecting it
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\ExcelSheet\\Ex01.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		//value stored in a string from excel
		String value = row.getCell(0).getStringCellValue();	
		driver.findElement(By.xpath("//*[@id=\"oneC_searchAutoComplete\"]")).sendKeys(value);
		Thread.sleep(4000);
	    
	}
	@When("Trutime is clicked")
	public void trutime_is_clicked() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//*[@id=\"newSearchAppsLST\"]/div[1]/div/div[2]")).click();
		Thread.sleep(5000);
	}
	@Then("Trutime is navigated")
	public void trutime_is_navigated() {
	    // Write code here that turns the phrase above into concrete actions
		
		driver.switchTo().frame("appFrame");
		WebElement plus=driver.findElement(By.xpath("//*[@id='mCSB_2_container']/div[2]/div[4]"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", plus);
	}
	@When("Legends are printed")
	public void legends_are_printed() {
	    // Write code here that turns the phrase above into concrete actions
		
		List<String> legends = new ArrayList<String>();
		for(int i=2;i<=30;i=i+2) { 
			String leg = driver.findElement(By.xpath("//*[@id='legendListID']/ul/li["+i+"]")).getText();
			legends.add(leg);   
		}
		System.out.println(legends);
	}
	@When("the current date is highlighted")
	public void the_current_date_is_highlighted() {
	    // Write code here that turns the phrase above into concrete actions
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM");
		String curformattedDate = currentDate.format(formatter);
		for(int i=2;i<=8;i++) {
			String trudate = driver.findElement(By.xpath("//*[@id='mCSB_2_container']/div[3]/div[" + i + "]/div[1]")).getText();
			dates.add(trudate);
		}
		WebElement high = null;
		for(int i=2,j=0;i<=8;i++,j++) {
			if(dates.get(j).equals(curformattedDate)) {
				high = driver.findElement(By.xpath("//*[@id='mCSB_2_container']/div[3]/div[" + i + "]/div[1]"));
				break;
			}
		}
		String highlighted=(String)((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('background-color');",high);
		if(!highlighted.equals("rgb(0, 0, 0)")){
			System.out.println("Current Date is highlighted");
		}
		else {
			System.out.println("Current Date is not highlighted");
		}
	}
	@When("the user checks whether the trutime and the system dates are equal")
	public void the_user_checks_whether_the_trutime_and_the_system_dates_are_equal()
	{
	    // Write code here that turns the phrase above into concrete actions
		java.util.List<String> sysdates= new java.util.ArrayList<String>();
		LocalDate today = LocalDate.now();
		LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM");
		for(int i=0;i<7;i++) {
		    sysdates.add(startOfWeek.plusDays(i).format(formatter));
		}
		if(dates.equals(sysdates))
		{
			System.out.println("System Dates and TruTime Dates are Equal");
		}
		else
		{
			System.out.println("System Dates and TruTime Dates are Not Equal");
		}
	}
	@When("the date until when the topup can be done is checked")
	public void the_date_until_when_the_topup_can_be_done_is_checked() 
	{
	    // Write code here that turns the phrase above into concrete actions
		LocalDate currentDate = LocalDate.now();
		LocalDate before15days = currentDate.minusDays(15);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM");
		String formattedDate = before15days.format(formatter);
		String topupdate = driver.findElement(By.xpath("//*[@id='mCSB_2_container']/div[1]/div[5]/div[1]/div[2]/p/span")).getText();
		if(formattedDate.equals(topupdate)) {
			System.out.println("Dates are matching");
		}
		else {
			System.out.println("Dates are not matching");
		}
	}
	@Then("the current month and year is checked")
	public void the_current_month_and_year_is_checked() 
	{
	    // Write code here that turns the phrase above into concrete actions
		DateTimeFormatter monthformatter = DateTimeFormatter.ofPattern("MMMM");
		LocalDate currentDate = LocalDate.now();
		String currentMonth = currentDate.format(monthformatter);
		String tcurrentMonth = driver.findElement(By.xpath("//*[@id='datepicker']/div/div/div/span[1]")).getText();
		//year
		DateTimeFormatter yearformatter = DateTimeFormatter.ofPattern("yyyy");
		String currentYear = currentDate.format(yearformatter);
		String tcurrentYear = driver.findElement(By.xpath("//*[@id='datepicker']/div/div/div/span[2]")).getText();
		if(currentMonth.equals(tcurrentMonth) && currentYear.equals(tcurrentYear)) {
			System.out.println("Month and Year are verified");
		}
		else {
			System.out.println("Month and Year are incorrect");
		}
		driver.quit();
	}
	}
