package pageObjPattern.pages.myAccount;

import org.openqa.selenium.By;
import pageObjPattern.basePage.MainPage;

/**
 * Created with IntelliJ IDEA.
 * User: vanle
 * Date: 10.08.14
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class myAccountPage extends MainPage{

    public By geyFirst_nameField(){
        return By.name("first_name");
    }

    public By getLastNameField(){
        return By.name("last_name");
    }

}
