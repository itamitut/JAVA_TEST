package ru.stqa.sandbox;

import JAVA_TEST.Sandbox.FirstLesson.Point;
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
@Test
    public void testDistance2(){
        Point A = new Point(2.7,5.3);
        Point B = new Point(1.2,-6.4);
        Assert.assertEquals(B.distance(A) , A.distance(B));
    }
// Проверим Теорему Пифагора:
@Test
    public void testPyphagoras(){
    Point A = new Point(-1.0,-1.0);
    Point B = new Point(-1.0,2.0);
    Point C = new Point(3.0,-1.0);
    Assert.assertEquals( Math.pow(A.distance(B),2.0)
            + Math.pow(A.distance(C),2.0), Math.pow(B.distance(C),2.0));

    }
}
