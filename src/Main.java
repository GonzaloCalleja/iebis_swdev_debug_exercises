public class Main {

        public static void main(String [] args) {
            double d = 0.0;

            while (d - 1.0 < 0.0000001) {
                d += 0.1;
            }

            System.out.println("d is 1");
        }
}
