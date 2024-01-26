package example.service;

public class AreaChecker {
    public static boolean checkHit(double x, double y, double r){
        if(x >= 0 && y >= 0){
            return (x <= r) && (y <= r / 2) && (x <= (r - 2 * y));
        }
        if (x <= 0 && y >= 0){
            return (-x <= r / 2) && (y <= r);
        }
        if (x <= 0 && y <= 0){
            return (x * x + y * y) <= r * r;
        }
        return false;
    }
}
