package array;

/**
 * You are given two strings word1 and word2.
 * Merge the strings by adding letters in alternating order, starting with word1.
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 * <p>
 * Return the merged string.
 * <p>
 * Example 1:
 * <p>
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 * Explanation: The merged string will be merged as so:
 * word1:  a   b   c
 * word2:    p   q   r
 * merged: a p b q c r
 * <p>
 * Example 2:
 * <p>
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 * Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 * word1:  a   b
 * word2:    p   q   r   s
 * merged: a p b q   r   s
 * <p>
 * Example 3:
 * <p>
 * Input: word1 = "abcd", word2 = "pq"
 * Output: "apbqcd"
 * Explanation: Notice that as word1 is longer, "cd" is appended to the end.
 * word1:  a   b   c   d
 * word2:    p   q
 * merged: a p b q c   d
 */
public class MergeStringsAlternately {

    public static void main(String[] args) {
        System.out.println(mergeAlternately("abcd", "pq"));
    }


    public static String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int l1 = word1.length();
        int l2 = word2.length();

        int size = Math.max(l1, l2);

        for (int i = 0; i < size; i++) {
            if (i < l1) {
                result.append(word1.charAt(i));
            }
            if (i < l2) {
                result.append(word2.charAt(i));
            }
        }

        return result.toString();
    }
}
