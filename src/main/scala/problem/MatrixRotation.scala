package problem

/**
 * You are given a 2D matrix, a, of dimension MxN and a positive integer R. You have to rotate the matrix R times and print the resultant matrix.
 * Rotation should be in anti-clockwise direction.
 *
 * Input Format
 * First line contains three space separated integers, M, N and R, where M is the number of rows, N is number of columns in matrix,
 * and R is the number of times the matrix has to be rotated. Then M lines follow, where each line contains N space separated positive integers.
 * These M lines represent the matrix.
 *
 * Sample Input
 * 4 4 1
 * 1 2 3 4
 * 5 6 7 8
 * 9 10 11 12
 * 13 14 15 1
 *
 * Sample Output
 *
 *2 3 4 8
 * 1 7 11 12
 * 5 6 10 16
 * 9 13 14 15
 *
 * See <a href="https://www.hackerrank.com/challenges/matrix-rotation-algo/problem">https://www.hackerrank.com/challenges/matrix-rotation-algo/problem/a>
 */
object MatrixRotation {
  case class Point(x: Int, y: Int)

  case class Tile(point: Point, element: Int)

  def rotateRight[A](list: Seq[A], i: Int): Seq[A] = {
    val size = list.size
    list.drop(size - (i % size)) ++ list.take(size - (i % size))
  }

  def convertMatrixIntoRing(matrix: Array[Array[Int]], iteration: Int, rotate: Int): Array[Tile] = {
    val m = (matrix.length - 1) - iteration
    val n = matrix.headOption.map(_.length - 1).getOrElse(0) - iteration
    val ring: Array[Tile] = firstOps(matrix, iteration, n, n + 1 - iteration) ++ secondOps(
      matrix,
      iteration + 1,
      iteration,
      m - iteration
    ) ++ thirdOps(matrix, m, iteration + 1, n - iteration) ++ fourthOps(matrix, m - 1, n, m - 1 - iteration)
    val point         = ring.map(_.point)
    val rotatedValues = rotateRight(ring.map(_.element), rotate)
    point.zip(rotatedValues).map { case (p, v) => Tile(p, v) }
  }

  def firstOps(matrix: Array[Array[Int]], mMin: Int, nMax: Int, elementCount: Int): Array[Tile] =
    Array.tabulate(elementCount)(i => Tile(Point(mMin, nMax - i), matrix(mMin)(nMax - i)))

  def secondOps(matrix: Array[Array[Int]], mMin: Int, nMin: Int, elementCount: Int): Array[Tile] =
    Array.tabulate(elementCount)(i => Tile(Point(mMin + i, nMin), matrix(mMin + i)(nMin)))

  def thirdOps(matrix: Array[Array[Int]], mMax: Int, nMin: Int, elementCount: Int): Array[Tile] =
    Array.tabulate(elementCount)(i => Tile(Point(mMax, nMin + i), matrix(mMax)(nMin + i)))

  def fourthOps(matrix: Array[Array[Int]], mMax: Int, nMax: Int, elementCount: Int): Array[Tile] =
    Array.tabulate(elementCount)(i => Tile(Point(mMax - i, nMax), matrix(mMax - i)(nMax)))

  def listToTileMatrix(matrix: List[List[Int]]): List[Tile] = matrix.zipWithIndex.flatMap { row =>
    row._1.zipWithIndex.map(e => Tile(Point(row._2, e._2), e._1))
  }

  private def boardToPath(matrix: Seq[Tile]): Seq[Int] =
    matrix
      .sortBy(_.point)(Ordering.by(p => (p.x, p.y)))
      .map(_.element)

  private def printMatrix(list: Seq[Int], n: Int): Unit =
    list
      .grouped(n)
      .foreach(arr => println(arr.mkString(" ")))

  def main(args: Array[String]): Unit = {
    val testValue: Array[Array[Int]] = Array(
      Array(1, 2, 3, 4),
      Array(7, 8, 9, 10),
      Array(13, 14, 15, 16),
      Array(19, 20, 21, 22),
      Array(25, 26, 27, 28)
    )

    val m        = testValue.length
    val n        = testValue.headOption.map(_.length).getOrElse(0)
    val counter  = math.min(m, n) / 2
    val rotation = 7

    val resultMatrix: Seq[Tile] =
      (0 until counter).flatMap(i => convertMatrixIntoRing(testValue, i, rotation))
    printMatrix(boardToPath(resultMatrix), n)
  }
}
