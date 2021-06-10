import org.junit.Assert;
import org.junit.Test;

public class SummatorTest {
    @Test
    public void positiveNumbersTest() {
        Assert.assertEquals(Summator.sum(2, 7), 9);
    }

    @Test
    public void negativeNumbersTest() {
        Assert.assertEquals(Summator.sum(-3, -2), -5);
    }
}
