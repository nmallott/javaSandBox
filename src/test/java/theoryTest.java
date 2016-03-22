import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;


/**
 * Created by nicolas on 22/03/2016.
 */
@RunWith(Theories.class)
public class theoryTest {

    @Rule
    public TestName name = new TestName();

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @DataPoint
    public static int dp1 = -99;

    @DataPoint
    public static int dp2 = -88;

    @DataPoint
    public static int dp3 = -77;

    @Theory
    public void testOn(@TestedOn(ints = {1, 2, 10}) int param) {
        assumeThat(param, either(is(1)).or(is(2)));
        System.out.println("Param " + param);
        assertThat(param).isLessThan(3);
    }

    @Theory
    public void testDataPoint(int param, int param2, int param3) {
        System.out.println("test :" + name.getMethodName() + "Param " + param + "," + param2 + "," + param3);
        assertThat(param).isLessThan(3);
    }

    @Theory
    public void testErrorCollector(int param, int param2, int param3) {
        //Attention, errorCollector agit au niveau de chaque iteration !
        errorCollector.checkThat("Error " + param , param, CoreMatchers.equalTo(param2));
        errorCollector.checkThat("Error " + param , param, CoreMatchers.equalTo(param2));
    }


}
