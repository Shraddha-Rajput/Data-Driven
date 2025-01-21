package driverFactory;

import java.io.File;

import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class Driver_Script extends AppUtil  //we wnat pre and post consition so extend
{

	String inputpath="C:\\EClipse_Shraddha\\Eclipse Project\\DDT_FrameWork\\FileInput\\LoginData.xlsx";  //this i S FIle name not sheet nma
	String outputpath="C:\\EClipse_Shraddha\\Eclipse Project\\DDT_FrameWork\\Fileoutput\\DatadriverResults.xlsx";
	//give wt name u wnat after fileoutput\\
	// we hvae extended apputil contaiisn pre and psot so no need to write here gaina
    //whenever u want to define path of excel just define this varibale name as input and outpath
	
	@Test
	public void startest() throws Throwable
	{
		//create object of ExcelFileUtil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);

		//count no of rows in login sheet
		int rowcount = xl.rowcount("Login");//give sheet name
		Reporter.log("No of rows" + rowcount ,true);

		for(int i=1;i<=rowcount;i++)
		{
			//reading username and password from exccel and put in the weviste url
			String Username = xl.getCellData("Login",i, 0) ; //reading 1 column(username) of login excel  sheet name
			String Password = xl.getCellData("Login",i, 1) ; //reading 2 col (password) form login eceel sheet


			//calling verify login method from function librarry as it is static called byits class name
			boolean res = FunctionLibrary.verify_Login(Username, Password);

			if(res)
			{
				//if it  is true write login sucess in Result col of  sheet, and write pass in Status col of sheet 
			
				xl.setCellData("Login", i, 2,"Login sucesfully  ", outputpath); //writings login succs in Result(2) col of login sheet
				xl.setCellData("Login", i, 3, "PASS"  ,outputpath); //write pass in Status col(3) of login sheet //0,1,2,3
			}
			
			else
			{

				File scshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //take sc when TC fail and stor ein scshot
				org.apache.commons.io.FileUtils.copyFile(scshot,new File("./Screenshots/Iteration/"+i+"LoginPage.png"));
				
				//if it is false write login sucessin  into Result cell,write pass in Status cell 

				xl.setCellData("Login", i, 2,"Login Unsuceessfully  ", outputpath);
				xl.setCellData("Login", i, 3, "FAIL ", outputpath); 

				
			}
		}


	}



}
