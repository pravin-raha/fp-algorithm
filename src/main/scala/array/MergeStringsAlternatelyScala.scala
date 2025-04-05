package array

object MergeStringsAlternatelyScala {
  def main(args: Array[String]): Unit = {
    println(mergeAlternately("abcd", "pq"))
  }

  def mergeAlternately(word1: String, word2: String): String = {
    val result: StringBuilder = new StringBuilder
    val l1: Int               = word1.length
    val l2: Int               = word2.length

    val size: Int = Math.max(l1, l2)

    for (i <- 0 until size) {
      if (i < l1) {
        result.append(word1.charAt(i))
      }
      if (i < l2) {
        result.append(word2.charAt(i))
      }
    }

    result.toString
  }
}
