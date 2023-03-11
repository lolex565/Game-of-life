package conway;

public class Tools {
    public static void clearScreen() {
        System.out.print("\033\143");
        System.out.flush();
    }
}
