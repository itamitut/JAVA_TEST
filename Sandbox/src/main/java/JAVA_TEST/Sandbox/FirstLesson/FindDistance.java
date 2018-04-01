package JAVA_TEST.Sandbox.FirstLesson;

/**
 * Created by Сергей on 01.04.2018.
 */
public class FindDistance {
    public static void main(String[] args) {
        Point A = new Point(1.5,2.5);
        Point B = new Point(-0.5,-2.0);
        System.out.println("Расстояние между точками А и В равно "
                +Point.distance(A,B) );
    }
}