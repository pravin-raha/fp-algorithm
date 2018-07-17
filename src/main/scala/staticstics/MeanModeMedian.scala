package staticstics

/**
  * Given an array, X, of N integers, calculate and print the respective mean, median, and mode on separate lines.
  * If your array contains more than one modal value, choose the numerically smallest one.
  */
object MeanModeMedian {

  def main(args: Array[String]): Unit = {

    val n = scala.io.StdIn.readInt()
    val array = scala.io.StdIn.readLine().split(" ").toList.map(_.toInt)

    import java.text.DecimalFormat
    val df = new DecimalFormat("###.#")
    println(df.format(mean(array)))
    println(df.format(median(array)))
    println(df.format(mode(array)))
  }

  def mean(numbers: List[Int]): Float = numbers.sum.toFloat / numbers.length

  def mode(numbers: List[Int]): Int = {
    val numCount = numbers
      .groupBy(i => i)
      .mapValues(e => e.length)

    val maxCount = numCount.maxBy(_._2)._2

    numCount.filter(_._2 == maxCount).keys.min
  }

  def median(numbers: List[Int]): Float = if (numbers.length % 2 == 0) {
    val mid: Int = Math.floor(numbers.size / 2).toInt
    numbers.sorted.slice(mid - 1, mid + 1).sum.toFloat / 2
  } else {
    numbers.sorted(Ordering[Int].reverse).apply(numbers.length / 2)
  }

}
