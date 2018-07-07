package JAVA_TEST.Sandbox.FirstLesson;
import static java.lang.Math.*;
/**
 * Created by Сергей on 01.04.2018.
 */
public class Point {
    public double x;
    public double y;
    public Point(double x, double y){
    this.x = x;
    this.y = y;
    }
    public  double distance(Point p2){
        return sqrt(Math.pow((this.x-p2.x),2.0)+Math.pow((this.y-p2.y),2.0));
    }
}