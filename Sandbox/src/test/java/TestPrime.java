import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Сергей on 23.04.2018.
 */
    @Test
public class TestPrime {
   public void testPrime(){
       Assert.assertTrue(Primes.isPrime( Integer.MAX_VALUE ));
   }

}
