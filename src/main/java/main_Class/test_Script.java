package main_Class;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class test_Script {

	public static void main(String[] args)

	{

		WebDriver driver = new ChromeDriver();

		try {
			// Step 1: Navigate to FitPeo Homepage
			driver.get("https://www.fitpeo.com/");
			driver.manage().window().maximize();
			System.out.println("Navigated to FitPeo Homepage");

			// Step 2 : Navigate to the Revenue Calculator Page:
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			WebElement btn_Revenue_Calculator = driver.findElement(By.xpath("//*[text()= 'Revenue Calculator']"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			btn_Revenue_Calculator.click();
			Thread.sleep(5000);

			// Step 3 : Scroll Down to the Slider section:
			Actions action = new Actions(driver);
			action.scrollByAmount(0, 500).perform();
			Thread.sleep(5000);

			// Step 4 : Adjust the Slider

			WebElement slider = driver.findElement(By.xpath("//input[@type='range']")); // Replace with actual slider
			int sliderWidth = slider.getSize().width;
			System.err.println("width:" + sliderWidth);

			Actions actions = new Actions(driver);

			actions.clickAndHold(slider).moveByOffset((sliderWidth / 20) * 94, 0) // Adjust offset as needed
					.release().perform();

			// action.dragAndDropBy(slider, 58, 0);
			WebElement txt_InputSlider = driver
					.findElement(By.xpath("//*[text()='Medicare Eligible Patients']/../div/div//input"));

			txt_InputSlider.clear();
			txt_InputSlider.click();
			Thread.sleep(5000);
			String value = "820";
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].value='" + value + "';", txt_InputSlider);

			txt_InputSlider.click();

			Thread.sleep(5000);

			// step 5 : Update the Text Field:

			txt_InputSlider.clear();
			Thread.sleep(5000);
			String newValue = "823";
			jse.executeScript("arguments[0].value='" + newValue + "';", txt_InputSlider);
			actions.clickAndHold(slider).moveByOffset((sliderWidth / 20) * -38, 0) // Adjust offset as needed
					.release().perform();
			Thread.sleep(5000);

			// step 6 : Validate Slider Value:

			String input_Value = txt_InputSlider.getAttribute("value");
			System.out.println("slider value:" + input_Value);
			if (input_Value.equals("563")) {

				System.out.println("Value is matched");

			} else {
				System.out.println("Value not matched");

			}

			// Step 7 : Select CPT Codes:

			String cptCode[] = { "CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474" };

			for (int flag = 0; flag < cptCode.length; flag++) {
				WebElement txt_Cptcode = driver.findElement(By.xpath("//*[text()='" + cptCode[flag] + "']/..//input"));

				action.moveToElement(txt_Cptcode);
				action.perform();

				txt_Cptcode.click();
				Thread.sleep(5000);
			}

			// Step 8 : Validate Total Recurring Reimbursement for 820:

			WebElement txt_validateHeader = driver
					.findElement(By.xpath("//*[text()='Total Recurring Reimbursement for all Patients Per Month:']/p"));

			String header_Value = txt_validateHeader.getText();

			if (header_Value.contains("76005")) {

				System.out.println("Value is matched");

			} else {
				System.out.println("Value not matched");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the browser after completing the automation
			driver.quit();
			System.out.println("Automation completed successfully");
		}
	}

}
