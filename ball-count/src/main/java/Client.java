import java.util.Scanner;

/**
 * Created by setsuna on 2017/5/19.
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("please input a right string naming : ");
        String str = new Scanner(System.in).nextLine();
        System.out.println("the score is : " + new BowlingGame().gainResult(str));
    }
}
