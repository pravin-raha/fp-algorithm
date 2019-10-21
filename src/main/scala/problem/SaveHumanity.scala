package problem

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

/**
 *
 * Save Humanity
 * Oh!! Mankind is in trouble again. This time, it's a deadly disease spreading at a rate never seen before.
 * The need of the hour is to set up efficient virus detectors.
 * You are the lead at Central Hospital and you need to find a fast and reliable way to detect the footprints of the virus DNA in that of the patient.
 *
 * The DNA of the patient as well as of the virus consists of lowercase letters.
 * Since the collected data is raw, there may be some errors.
 * You will need to find all substrings in the patient DNA that either exactly match the virus DNA or have at most one mismatch,
 * i.e., a difference in at most one location.
 *
 * For example, "aa" and "aa" are matching, "ab" and "aa" are matching, while "abb" and "bab" are not.
 *
 * Function Description
 *
 * Complete the virusIndices function in the editor below. It should print a list of space-separated integers that represent the starting indices of matching substrings in increasing order, or No match!.
 */
object SaveHumanity {

  def virusIndices(dna: String, virus: String) {
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

  def main(args: Array[String]): Unit = {
    val dna   = "banana"
    val virus = "nan"

    virusIndices(dna, virus)
  }
}
