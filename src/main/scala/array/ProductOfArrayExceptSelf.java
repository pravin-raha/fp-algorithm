package array;

import java.util.Arrays;

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] leftMul = new int[nums.length];
        int[] rightMul = new int[nums.length];
        int[] resultMul = new int[nums.length];
        int leftTotal = 1;
        int rightTotal = 1;
        for (int i = 0; i < nums.length; i++) {
            int l = i - 1 >= 0 ? nums[i - 1] : 1;
            leftTotal = leftTotal * l;
            leftMul[i] = leftTotal;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            int l = i + 1 <= nums.length - 1 ? nums[i + 1] : 1;
            rightTotal = rightTotal * l;
            rightMul[i] = rightTotal;
        }

        for (int i = 0; i < nums.length; i++) {
            resultMul[i] = rightMul[i] * leftMul[i];
        }

        return resultMul;
    }
}
