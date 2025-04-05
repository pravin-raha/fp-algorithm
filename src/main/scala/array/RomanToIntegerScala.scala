package array

object RomanToIntegerScala {

  def main(args: Array[String]): Unit = {
    println(romanToInt("IV"))
  }

  def romanToInt(s: String): Int = {
    val d: Map[Char, Int] = Map
      .apply('I' -> 1, 'V' -> 5, 'X' -> 10, 'L' -> 50, 'C' -> 100, 'D' -> 500, 'M' -> 1000)

    var sum: Int  = 0
    val size: Int = s.length
    var i: Int    = 0
    while (i < size) {
      if ((size - 1) - i > 0 && d.get(s.charAt(i)).get < d.get(s.charAt(i + 1)).get) {
        sum = sum + d.get(s.charAt(i + 1)).get - d.get(s.charAt(i)).get
        i = i + 1
      } else sum = sum + d.get(s.charAt(i)).get

      i = i + 1
    }
    sum
  }
}
