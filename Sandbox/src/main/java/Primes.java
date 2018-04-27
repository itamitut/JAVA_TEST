
/**
 * Created by Сергей on 23.04.2018.
 */
public class Primes {
    public static boolean isPrime(int n){
       int i=2;
       while (i<n/2 && n%i !=0){
           i++;
       }
       return i == n ;
    }
}
