package array

/**
 * Given an integer array nums of size n, return the number with the value closest to 0 in nums.
 * If there are multiple answers, return the number with the largest value.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-4,-2,1,4,8]
 * Output: 1
 * Explanation:
 * The distance from -4 to 0 is |-4| = 4.
 * The distance from -2 to 0 is |-2| = 2.
 * The distance from 1 to 0 is |1| = 1.
 * The distance from 4 to 0 is |4| = 4.
 * The distance from 8 to 0 is |8| = 8.
 * Thus, the closest number to 0 in the array is 1.
 */
object FindClosestNumberToZero {

  def main(args: Array[String]): Unit = {
    val nums    = List(-4, -2, 1, 4, 8)
    var closest = nums.head;

    for (n <- nums) {
      if (Math.abs(n) < Math.abs(closest)) {
        closest = n
      }
    }

    if (closest < 0) {
      for (n <- nums) {
        if (n == Math.abs(closest)) {
          closest = n
        }
      }
    }

    println(closest)
  }

}
