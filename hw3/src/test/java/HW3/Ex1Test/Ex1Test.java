package HW3.Ex1Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = {"src/test/resources/Ex1.feature"},
        glue = {"HW3.Ex1steps"}
)
public class Ex1Test  extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
