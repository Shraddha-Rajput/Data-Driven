package commonFunctions;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary  extends AppUtil
//all methods will write here will access data from Excel
//will extend apputil as we  want LOgin Loactors also we can acces variable as driver and prt 
{

	public static boolean verify_Login(String user,String pass)
	{

		driver.get(prt.getProperty("Url"));  //we can access driver object from Apputil if will extend 
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
     //defing all vaalue sin proeprty file
		driver.findElement(By.cssSelector(prt.getProperty("Objuser"))).sendKeys(user); //here use same keys which declare baove in method argumnet

		driver.findElement(By.cssSelector(prt.getProperty("Objpass"))).sendKeys(pass);

		driver.findElement(By.cssSelector(prt.getProperty("Objlogin"))).click();

		String expected="dashboard";
		String actual =driver.getCurrentUrl();

		if(actual.contains(expected))
		{

			Reporter.log("Login Successfully" +expected+ " " +actual,true);
			return true;

		}

		else
			//capture error message

		{
			String errormessage= driver.findElement(By.cssSelector(prt.getProperty("Objerror"))).getText();

			Reporter.log(errormessage+ " "+expected+ " "  +actual  ,true);
			return false;

		}


 }
}
