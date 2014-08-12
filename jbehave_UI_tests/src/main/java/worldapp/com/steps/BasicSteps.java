package worldapp.com.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.firefox.FirefoxDriver;
import worldapp.com.common.SimpleProvider;
import worldapp.com.pages.PageFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created with IntelliJ IDEA.
 * User: ivan.halyavka
 * Date: 30.11.13
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
public class BasicSteps extends PageFactory {

    public BasicSteps(SimpleProvider driverProvider) {
        super(driverProvider);
    }

    private FirefoxDriver driver;

    @BeforeScenario
    public void beforeScenario(){
      //  webDriver = webDriverProvider.get();
      //  webDriver.manage().window().maximize();
    }

    @Given("open SelfInfo page")
    public void openSelfInfo(){
        driver = new FirefoxDriver();
       // driver.get("http://ivan.halyavka:aeGah0IeJe@" + AppConfig.getSurveyUrl() + "/SelfInfo");
    }

    @When("save pagesource of SelfInfo page")
    public void savePageSource(){
        String pageSource = driver.getPageSource();
        writeInFile(System.getProperty("user.dir") + "/SelfInfo.html", pageSource);
        driver.quit();
    }

    public void writeInFile(String sFileName, String sTextToWrite){
        FileWriter outFile;
        try {
            System.err.println("==== " + sFileName);
            outFile = new FileWriter(sFileName);
            PrintWriter out = new PrintWriter(outFile);
            out.print(sTextToWrite);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
