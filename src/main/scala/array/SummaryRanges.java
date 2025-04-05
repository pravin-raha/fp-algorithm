package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 * <p>
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 */
public class SummaryRanges {
    public static void main(String[] args) {
        summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9})
                .forEach(System.out::println);
    }

    public static List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }
        int startingPoint = nums[0];
        List<String> result = new ArrayList<>();
        int counter = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                if (startingPoint == nums[i]) {
                    result.add(String.valueOf(startingPoint));
                } else {
                    result.add(startingPoint + "->" + nums[i]);
                }
            } else if (startingPoint + counter != nums[i + 1]) {
                if (startingPoint == nums[i]) {
                    result.add(String.valueOf(startingPoint));
                    startingPoint = nums[i + 1];
                    counter = 0;
                } else {
                    result.add(startingPoint + "->" + nums[i]);
                    startingPoint = nums[i + 1];
                    counter = 0;
                }
            }
            counter++;
        }

        return result;
    }
}
