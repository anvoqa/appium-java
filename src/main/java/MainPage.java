import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MainPage extends BasePage{

    public MainPage(AndroidDriver driver){
        super(driver);
    }

    By equalButton = By.id("com.google.android.calculator:id/eq");
    By result = By.id("com.google.android.calculator:id/result_final");

    public void tapDigitNumber(int num){
        clickElement(By.id("com.google.android.calculator:id/digit_" + num));
    }

    public enum Operators {
        ADD("add"),
        SUBTRACT("sub"),
        MULTIPLY("mul"),
        DIVIDE("div");

        private String opreratorElementBy;

        Operators(String opr){
            this.opreratorElementBy = "com.google.android.calculator:id/op_" + opr;
        }

        private String getOperatorElementBy(){
            return this.opreratorElementBy;
        }
    }

    public void tapOperator(Operators opr){
        clickElement(By.id(opr.getOperatorElementBy()));
    }

    public void tapEqual(){
        clickElement(equalButton);
    }

    public String getResult(){
        return getTextFromElement(result);
    }
}
