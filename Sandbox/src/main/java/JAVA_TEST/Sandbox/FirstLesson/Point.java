package JAVA_TEST.Sandbox.FirstLesson;
import static java.lang.Math.*;
/**
 * Created by Сергей on 01.04.2018.
 */
public class Point {
    public double x;
    public double y;
    Point(double x,double y){
    this.x = x;
    this.y = y;
    }
    public static double distance(Point p1, Point p2){
        return sqrt(Math.pow((p1.x-p2.x),2.0)+Math.pow((p1.y-p2.y),2.0));
    }
}