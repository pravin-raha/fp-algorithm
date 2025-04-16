package search;

public class BinarySearchJava {

    // 1. Sort array
    // 2. Calculate mid
    // 3. Check if mid-value is equal to key if true return true
    // 4. Else check if mid-value is grater that key search key in right array
    // 4. Else search key in left array
    private static boolean search(int[] array, int key) {

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == key) {
                return true;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{1, 2, 4, 5, 6, 7, 8, 9}, 5));
    }
}
