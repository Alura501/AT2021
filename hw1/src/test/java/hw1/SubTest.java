package hw1;


import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubTest {
    Calculator calculator;

    @DataProvider(name = "double")
    public static Object[][] doubleDataProvider(){
        return new Object [][]{
                {2.7, 4.2},
                {12.6, 4.2},
                {-1.7, 32.3},
                {-10.3, 0.2},
                {3.3, -4.2},
                {38, 0},
                {-75.3223, -23},
                {32.2, 24.2}
        };
    }

    @DataProvider(name = "long")
    public static Object[][] longDataProvider(){
        return new Object [][]{
                {0L, 0L},
                {32L, 4L},
                {-24L, 32L},
                {-23L, 0L},
                {4L, -4L},
                {Long.MIN_VALUE, 0},
                {-75L, Long.MAX_VALUE},
                {32L, 24L}
        };
    }


    @BeforeClass
    public void init(){
        calculator = new Calculator();
    }


    @Test(testName = "SubTest double",
            dataProvider = "double")
    public void subTestDouble(double first, double second){
        Assert.assertEquals(calculator.sub(first, second), first-second);
    }

    @Test(testName = "SubTest long",
            dataProvider = "long")
    public void subTestLong(long first, long second){
        Assert.assertEquals(calculator.sub(first, second), first-second);
    }
}
