package JAVA_TEST.Sandbox.FirstLesson;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Сергей on 02.04.2018.
 */
public class DistantTest {
@Test
    public void testDistance(){
    Point A = new Point(1.0,2.0);
    Point B = new Point(1.0,-2.0);
    Assert.assertEquals(4.0 , A.distance(B));
    }
}
