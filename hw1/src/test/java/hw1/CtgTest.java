package hw1;


import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CtgTest {
    Calculator calculator;

    @DataProvider(name = "double")
    public static Object[][] doubleDataProvider(){
        return new Object [][]{
                {2.7},
                {12.6},
                {-1.7},
                {-10.3},
                {3.3},
                {0.0},
                {Double.MAX_VALUE},
                {Double.MIN_VALUE}
        };
    }


    @BeforeClass
    public void init(){
        calculator = new Calculator();
    }


    @Test(testName = "CtgTest double",
            dataProvider = "double")
    public void сtgTestDouble(double alpha){
        Assert.assertEquals(calculator.ctg(alpha), (double)Math.cos(alpha)/Math.sin(alpha));
    }

}
