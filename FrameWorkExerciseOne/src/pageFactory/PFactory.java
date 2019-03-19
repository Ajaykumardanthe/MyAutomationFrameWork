package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PFactory {

	@CacheLookup
	@FindBy(how = How.NAME, using = "firstname")
	public static WebElement firstName;

	@CacheLookup
	@FindBy(how = How.NAME, using = "lastname")
	public static WebElement lastName;

	public PFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	@CacheLookup
	@FindBy(how = How.NAME, using = "submit")
	public static WebElement submitButton;

	@FindBy(how = How.ID, using = "datepicker")
	public static WebElement date;

}
