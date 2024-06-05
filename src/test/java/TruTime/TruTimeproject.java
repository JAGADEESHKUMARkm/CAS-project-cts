package TruTime;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import UserInfo.Onecog;
import UserInfo.Tru;
import io.github.bonigarcia.wdm.WebDriverManager;
import UserInfo.Becog;
 

@Listeners(ExtendedReport.ExtenttReportss.class)

public class TruTimeproject {
	public static WebDriver driver;
	private static List<String> dates = new ArrayList<String>();

	@BeforeSuite
	public void  getDriver() throws InterruptedException
	{
		//DriverSetup
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter \"1\" to open \"Chrome\" or Enter \"2\" to open \"Edge\": ");
		String be=sc.nextLine();	
		if(be.equals("1")) {
	    driver = new ChromeDriver();
		}
		if(be.equals("2")) {
			driver = new EdgeDriver();
		}
		
		//opening Url
		Becog.getdriver(driver);
		//driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@Test(priority=0)

	public void userInfo() throws IOException, InterruptedException
	{
		Thread.sleep(5000);	 
		Becog.userinformation(driver).click();
		Becog.username(driver).getText();
		Becog.email(driver).getText();
		System.out.println(Becog.username(driver).getText());
		System.out.println(Becog.email(driver).getText());
		
		FileInputStream f = new FileInputStream(System.getProperty("user.dir")+("\\ExcelSheet\\Ex02.xlsx")); //locating file from directory
		XSSFWorkbook wbook = new XSSFWorkbook(f); //getting the workbook
		XSSFSheet sh = wbook.getSheetAt(0); //getting index 0 sheet 
		XSSFRow rows = sh.getRow(0); //getting index 0 row 
		
		//value stored in a string from excel
		String username = rows.getCell(0).getStringCellValue();	//line gets value from first cell and cconvert it to string
		String emailid = rows.getCell(1).getStringCellValue();
		Thread.sleep(3000);
		if(Becog.username(driver).getText().equals(username)&& Becog.email(driver).getText().equals(emailid))
		{
			System.out.println("User Information is verified");
		}
		else
		{
			System.out.println("User Information is Not verified");
		}
		Thread.sleep(3000);
		Becog.userinformation(driver).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(Becog.userinformation(driver)));
		

		
		//Scroll Down
		
		//Becog.sdown(driver);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//div[@class='title_6422cef7']")));
		Thread.sleep(4000);
		TakesScreenshot ts=((TakesScreenshot)driver);
		File srcfile=ts.getScreenshotAs(OutputType.FILE);
		File trgfile= new File("C:\\Users\\2303984\\eclipse-workspace\\CAS\\Screenshot\\Becog.png");
		FileUtils.copyFile(srcfile, trgfile);
	}

	@Test(priority=1)

	public void oneCognizant() throws InterruptedException, IOException
	{
		//Clicking on OneCognizant
		Becog.onec(driver).click();

		//ScreenShot
		TakesScreenshot ts=((TakesScreenshot)driver);
		File srcfile1=ts.getScreenshotAs(OutputType.FILE);
		File trgfile1= new File("C:\\Users\\2303984\\eclipse-workspace\\CAS\\Screenshot\\onecog.png");
		FileUtils.copyFile(srcfile1, trgfile1);
		
		if(Becog.onec(driver)!=null)
			System.out.println("OneCognizant is Present");
		else
			System.out.println("OneCognizant is Absent");
		
		Set<String>handles=driver.getWindowHandles();
		for (String newwindow : handles) 
		{
			driver.switchTo().window(newwindow);
		}
		Thread.sleep(4000);
	}

	@Test(priority=2)

	public void navigateTrutime() throws InterruptedException, IOException
	{
		//clk search btn
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(Onecog.search(driver)));
		
		//sending trutime
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\ExcelSheet\\Ex01.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		String value = row.getCell(0).getStringCellValue();	
		Onecog.search(driver).sendKeys(value);
		
		TakesScreenshot ts=((TakesScreenshot)driver);
		File srcfile2=ts.getScreenshotAs(OutputType.FILE);
		File trgfile2= new File("C:\\Users\\2303984\\eclipse-workspace\\CAS\\Screenshot\\Navigate_Trutime.png");
		FileUtils.copyFile(srcfile2, trgfile2);
		Thread.sleep(4000);
		
		//clk search btn
		Onecog.tticon(driver).click();
	}

	@Test(priority=3)

	public void scrollDown() throws InterruptedException, IOException
	{
		//moving to frame
		Thread.sleep(5000);
		driver.switchTo().frame("appFrame");
		
		//Scroll down
		Onecog.sdowns(driver);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Onecog.sdowns(driver));
		
		//ScreenShot
		TakesScreenshot ts=((TakesScreenshot)driver);
		File srcfile3=ts.getScreenshotAs(OutputType.FILE);
		File trgfile3= new File("C:\\Users\\2303984\\eclipse-workspace\\CAS\\Screenshot\\Scrolldown.png");
		FileUtils.copyFile(srcfile3, trgfile3);
	}

	@Test(priority=4)

	public void printingLegends()
	{
		//Printing Legends
		// 1. getting xpath and adding in list forloop is used for remving boxes
		List<String> legends = new ArrayList<String>();
		for(int i=2;i<=30;i=i+2) { //used for removing boxes here even format is only printed
			String leg = driver.findElement(By.xpath("//*[@id='legendListID']/ul/li["+i+"]")).getText();
			legends.add(leg);    
		}
		System.out.println(legends);
	}

	@Test(priority=5)

	public void highlightedDate()
	{
		// 2. getting current date and getting xpath date and checks sys date and xpath date equal means it checks color
		//Checking currentdate is Highlighted or not
		LocalDate currentDate = LocalDate.now(); //getting system date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM"); //formatter
		String curformattedDate = currentDate.format(formatter); //formted here
		
		//getting trutime dates in forloop
		for(int i=2;i<=8;i++) {
			String trudate = driver.findElement(By.xpath("//*[@id='mCSB_2_container']/div[3]/div[" + i + "]/div[1]")).getText();
			//addding the trutime in list named dates
			dates.add(trudate);
		}
		
		//checking here that system date and trutime date equal means it goes to check the color.
		WebElement high = null;
		for(int i=2,j=0;i<=8;i++,j++) {
			if(dates.get(j).equals(curformattedDate)) {
				 high = driver.findElement(By.xpath("//*[@id='mCSB_2_container']/div[3]/div[" + i + "]/div[1]"));
				break;
			}
		}
		
		//js executor is used here to get background color, high is mixed there, it is the dates got in trutime list
		String highlighted=(String)((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('background-color');",high);
		
		//not black means it is highlighted
		if(!highlighted.equals("rgb(0, 0, 0)")){
			System.out.println("Current Date is highlighted");
		}
		else {
			System.out.println("Current Date is not highlighted");
		}
	}
	
	@Test(priority=6)

	public void comparingDates()
	{
		//getting sys date and trudate equaling it if 2 also equal means equal 
		//Comparing System dates and current week dates
		java.util.List<String> sysdates= new java.util.ArrayList<String>();
		LocalDate today = LocalDate.now();
		// changing the system date starting with sunday
		LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		//and changing its format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM");
		//adding the date in list sysdates
		for(int i=0;i<7;i++) {
		    sysdates.add(startOfWeek.plusDays(i).format(formatter));
		}
		//checking the system date with trutime date
		if(dates.equals(sysdates))
		{
			System.out.println("System Dates and TruTime Dates are Equal");
		}
		else
		{
			System.out.println("System Dates and TruTime Dates are Not Equal");
		}
	}

	@Test(priority=7)

	public void before15Days()
	{
		//geting sys date reducing it into minus 15 days comparing trutime date with sys
		//getting system date
		LocalDate currentDate = LocalDate.now();
		//reducing 15 days before
		LocalDate before15days = currentDate.minusDays(15);
		//formatting that date into that format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM");
		String formattedDate = before15days.format(formatter);
		String topupdate = driver.findElement(By.xpath("//*[@id='mCSB_2_container']/div[1]/div[5]/div[1]/div[2]/p/span")).getText();
		//comparing the before 15days trutime with system
		if(formattedDate.equals(topupdate)) {
			System.out.println("Dates are matching");
		}
		else {
			System.out.println("Dates are not matching");
		}
	}

	@Test(priority=8)

	public void monthAndYear()
	{
		//getting sys month and year checking with trutime equal or nor. 
		//month
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter monthformatter = DateTimeFormatter.ofPattern("MMMM");
		String currentMonth = currentDate.format(monthformatter);
		Tru.month(driver).getText();
		
		//year
		DateTimeFormatter yearformatter = DateTimeFormatter.ofPattern("yyyy");
		String currentYear = currentDate.format(yearformatter);
		Tru.year(driver).getText();
		if(currentMonth.equals(Tru.month(driver)) && currentYear.equals(Tru.year(driver))) {
			System.out.println("Month and Year are verified");
		}
		else {
			System.out.println("Month and Year are incorrect");
		}
	}

	@AfterSuite

	public void closeBrowser()
	{
		System.out.print("Completed....");
		driver.quit();
	}
}