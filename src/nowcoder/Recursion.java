package nowcoder;

public class Recursion {
    int lifeTime = 10;

    public static void main(String[] args) {
        new Recursion().recursion(0);
    }

    public void recursion(int i) {
        if (i < lifeTime) {
            System.out.print(i+" ");
            recursion(++i);
            System.out.print(i-1+" ");
        } else {
            System.out.println();
            System.out.println("reverse");
        }
    }
}
