public class GetAdditionOfMultiple {

    public static int getSomeOfMultiple(int x, int y, int lastNumber) {
        int sum = 0;
        for (int i = 1; i < lastNumber; i++) {
            if (i % x == 0 || i % y == 0) {
                sum = sum + i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int sum = getSomeOfMultiple(3, 5, 1000);
        System.out.println(sum);
    }
}
