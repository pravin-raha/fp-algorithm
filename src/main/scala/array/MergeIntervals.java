package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * <p>
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {4, 5}})));

    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int[] lastInterval = intervals[0];
        int[][] result = new int[intervals.length][2];
        result[0] = lastInterval;
        int lastIntervalCounter = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            if (currentInterval[0] <= lastInterval[1]) {
                lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
                result[lastIntervalCounter] = lastInterval;
            } else {
                lastIntervalCounter++;
                result[lastIntervalCounter] = currentInterval;
                lastInterval = currentInterval;
            }

        }
        return Arrays.copyOfRange(result, 0, lastIntervalCounter + 1);
    }
}
