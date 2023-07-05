import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private AndroidDriver<AndroidElement> driver;
    private WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);//Set explicit timeout
    }

    private AndroidElement findElement(By by){
        try{
            return driver.findElement(by);
        } catch (NoSuchElementException e){
            System.out.println("Cannot find element!\n" + e.getMessage());
        }
        return null;
    }

    private void waitForElementVisible(By by){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (ElementNotVisibleException e){
            System.out.println("Element is not visible!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void clickElement(By by){
        waitForElementVisible(by);
        findElement(by).click();
    }

    protected void sendKeysToElement(By by, String value){
        waitForElementVisible(by);
        if(!findElement(by).getText().isEmpty()){
            findElement(by).clear();
        }
        findElement(by).sendKeys(value);
    }

    protected String getTextFromElement(By by){
        waitForElementVisible(by);
        return findElement(by).getText();
    }
}
