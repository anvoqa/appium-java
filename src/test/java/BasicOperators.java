import org.testng.Assert;
import org.testng.annotations.*;

public class BasicOperators extends BaseTest{
        private MainPage mainPage;

        @BeforeMethod
        public void setup() {
            mainPage = new MainPage(driver);
        }

        @Test
        public void sumTest() {
            mainPage.tapDigitNumber(2);
            mainPage.tapOperator(MainPage.Operators.ADD);
            mainPage.tapDigitNumber(3);
            mainPage.tapEqual();
            Assert.assertEquals(mainPage.getResult(), "5");
        }

        @Test
        public void subtractTest() {
            mainPage.tapDigitNumber(9);
            mainPage.tapOperator(MainPage.Operators.SUBTRACT);
            mainPage.tapDigitNumber(5);
            mainPage.tapEqual();
            Assert.assertEquals(mainPage.getResult(), "4");
        }

}
