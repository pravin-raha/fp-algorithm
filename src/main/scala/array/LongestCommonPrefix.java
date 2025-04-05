package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * <p>
 * Example 2:
 * <p>
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        StringBuilder longest = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            Set<Character> s = new HashSet<>();
            for (String str : strs) {
                if (str.length() <= i) {
                    return longest.toString();
                }
                s.add(str.charAt(i));
            }
            if (s.size() > 1) {
                return longest.toString();
            }
            longest.append(s.stream().findFirst().get());
        }
        return longest.toString();
    }
}
