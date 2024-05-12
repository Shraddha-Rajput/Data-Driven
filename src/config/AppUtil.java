package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil
{
	//here will acces url and loactors from Proeprty file as this class associtae  with proeprty kos and mention here pre and post condition

	public static Properties prt;  //declaring global we can acess ina nother class
	public static WebDriver driver ;

	@BeforeTest
	public static  void setUp() throws Throwable, IOException  //not retrning anythign
	{
		prt= new Properties();
		prt.load(new FileInputStream("C:\\EClipse_Shraddha\\Eclipse Project\\DDT_FrameWork\\PropertyFile\\Environment.properties"));

		if(prt.getProperty("Browser").equalsIgnoreCase("chrome"))  // here Browser is the key and chrome is its value which is define in property file
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}

		else if (prt.getProperty("Browser").equalsIgnoreCase("Firebox"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		//if bith not then print message

		else
		{
			Reporter.log("Browser value is not matching");

		}
	}

	@AfterTest
	public void tearDown()
	{

		driver.close();
	}


}
