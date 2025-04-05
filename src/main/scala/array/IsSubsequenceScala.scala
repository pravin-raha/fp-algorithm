package array

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 */
object IsSubsequenceScala {
  def main(args: Array[String]): Unit = {
    println(isSubsequence("aaaaaa", "bbaaaa"))
  }

  def isSubsequence(s: String, t: String): Boolean = {
    var sp = 0
    var tp = 0

    while (sp < s.length && tp < t.length) {
      if (s.charAt(sp) == t.charAt(tp)) {
        sp = sp + 1
      }
      tp = tp + 1
    }
    sp == s.length
  }

}
