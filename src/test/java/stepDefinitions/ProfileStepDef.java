package stepDefinitions;

import static org.junit.Assert.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPageObj;
import pageObjects.ProfilePageObj;

public class ProfileStepDef {
	
	WebDriver driver;
	LoginPageObj lp;
	ProfilePageObj pp;
	WebDriverWait wait=new WebDriverWait(Base.getDriver(),Duration.ofSeconds(15));
	
	@Given("I navigate to Naukri Application")
	public void i_navigate_to_naukri_application() {
		lp=new LoginPageObj(Base.getDriver());
		lp.selectLogin();
		
	}

	@Given("enter the credentials")
	public void enter_the_credentials() {

lp.enterCredentials("ingleajay1297@gmail.com", "AjayIB@1203");
		lp.selectLoginButton();
	}
	@Given("I naviagte to my profile")
	public void i_naviagte_to_my_profile() throws InterruptedException {
		//wait.wait(2000);
		pp=new ProfilePageObj(Base.getDriver());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("View")));
	    pp.clickViewProfile();
	    //pp.closeChat();
	}
	@Given("Click on carrer profile")
	public void click_on_carrer_profile() throws InterruptedException {
	    pp.clickCarrerProfile();
	    Thread.sleep(3000);
	    pp.editCarrerProfile();
	    Thread.sleep(3000);
	    boolean res=pp.verifyFormIsPresent();
	    assertTrue(res);
	}
	@When("I click on {string}, select\\/deslect a {string}")
	public void i_click_on_select_deslect_a(String string, String string2) throws InterruptedException {
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("locationSugg")));
		Thread.sleep(4000);
		pp.clickForLocation();
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Chennai']/i")));
	    boolean isSelected=pp.isLocationSelected();
	    if(isSelected)
	    {
	    	pp.addLocation();
	    	Thread.sleep(3000);
	    	System.out.print("Added Chennai to location prefernce");
	    }
	    else
	    {
	    	pp.addLocation();
	    	Thread.sleep(3000);
	    	System.out.print("Removed Chennai to location prefernce");
	    }
	    pp.clickLocationLabel();
	    Thread.sleep(3000);
	}
	@When("clcik on save button")
	public void clcik_on_save_button() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saveDesiredProfile")));
		pp.clickcpSaveButton();
		Thread.sleep(3000);
	}
	@Then("I should see profile updated as {string}")
	public void i_should_see_message(String exp_text) throws InterruptedException {
	    String msg=pp.verifyProfileUpdateStatus();
	    Thread.sleep(3000);
	    assertTrue(msg.contains(exp_text));
	}
	@When("Click on {string} in resume")
	public void click_on_in_resume(String string) throws InterruptedException {
		Thread.sleep(3000);
	    pp.clickUpdateLink();
	    Thread.sleep(3000);
	}

	@When("I click on resume that has to be uploaded")
	public void i_click_on_resume_that_has_to_be_uploaded() throws InterruptedException {
	    pp.uploadResume();
	    Thread.sleep(3000);
	}

	@Then("Resume should br uploaded successfully")
	public void resume_should_br_uploaded_successfully() throws InterruptedException {
	    String name=pp.validateResumeName();
	    Thread.sleep(3000);
	    assertTrue(name.contains("STE_AjayIngle3_5.pdf"));
	    
	}
}
