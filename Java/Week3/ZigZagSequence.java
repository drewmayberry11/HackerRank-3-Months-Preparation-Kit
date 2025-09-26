import java.util.Arrays;

public class ZigZagSequence {

    public static void main(String[] args) {

        int[] a = { 2, 3, 5, 1, 4 };
        int n = a.length;
        Arrays.sort(a);

        int mid = (n + 1) / 2;
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;

    }

}
