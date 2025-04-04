package array;

/**
 * Given an integer array nums of size n, return the number with the value closest to 0 in nums.
 * If there are multiple answers, return the number with the largest value.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-4,-2,1,4,8]
 * Output: 1
 * Explanation:
 * The distance from -4 to 0 is |-4| = 4.
 * The distance from -2 to 0 is |-2| = 2.
 * The distance from 1 to 0 is |1| = 1.
 * The distance from 4 to 0 is |4| = 4.
 * The distance from 8 to 0 is |8| = 8.
 * Thus, the closest number to 0 in the array is 1.
 */
public class FindClosestNumberToZeroJava {

    public static void main(String[] args) {
        System.out.println(findClosestNumber(new int[]{-4, -2, 1, 4, 8}));
    }

    public static int findClosestNumber(int[] nums) {

        var closest = nums[0];

        for (int n : nums) {
            if (Math.abs(n) < Math.abs(closest)) {
                closest = n;
            }
        }

        // Check if we have positive value for closest number
        if (closest < 0) {
            for (int n : nums) {
                if (n == Math.abs(closest)) {
                    return n;
                }
            }
        }
        return closest;
    }
}

