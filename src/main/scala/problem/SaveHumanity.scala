package problem

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

object SaveHumanity {

  def main(args: Array[String]): Unit = {
    val dna       = "atcgatcga" // banana nan
    val virus     = "cgg"
    val dnaSize   = dna.length
    val virusSize = virus.length
    var noOfDiff  = 0
    val index     = new ListBuffer[Int]()

    var i = 0
    breakable {
      while (i <= dnaSize - virusSize) {
        noOfDiff = 0
        var j = i
        breakable {
          while (j <= i + virusSize - 1) {
            if (dna.charAt(j) != virus.charAt(j - i)) {
              noOfDiff += 1
              if (noOfDiff >= 2)
                break
            }
            j += 1
          }
        }
        if (noOfDiff < 2) {
          index += i
        }
        i += 1
      }
    }

    if (index.nonEmpty) println(index.mkString(" ")) else println("No Match!")
  }

}
