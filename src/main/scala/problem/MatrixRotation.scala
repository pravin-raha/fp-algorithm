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

  def rotateRight[A](list: List[A], i: Int): List[A] = {
    val size = list.size
    list.drop(size - (i % size)) ++ list.take(size - (i % size))
  }

  def convertMatrixIntoRing(matrix: List[List[Int]], iteration: Int, rotate: Int): List[Tile] = {
    val tiles = listToTileMatrix(matrix)
    val m     = (matrix.size - 1) - iteration
    val n     = matrix.headOption.map(_.size - 1).getOrElse(0) - iteration
    val ring: List[Tile] = firstOps(tiles, iteration, n, n + 1 - iteration) ::: secondOps(
      tiles,
      iteration + 1,
      iteration,
      m - iteration
    ) ::: thirdOps(tiles, m, iteration + 1, n - iteration) ::: fourthOps(tiles, m - 1, n, m - 1 - iteration)
    val point         = ring.map(_.point)
    val rotatedValues = rotateRight(ring.map(_.element), rotate)
    point.zip(rotatedValues).map { case (p, v) => Tile(p, v) }
  }

  def firstOps(matrix: List[Tile], mMin: Int, nMax: Int, elementCount: Int): List[Tile] =
    List.tabulate(elementCount)(i => matrix.find(t => t.point == Point(mMin, nMax - i)).get)

  def secondOps(matrix: List[Tile], mMin: Int, nMin: Int, elementCount: Int): List[Tile] =
    List.tabulate(elementCount)(i => matrix.find(t => t.point == Point(mMin + i, nMin)).get)

  def thirdOps(matrix: List[Tile], mMax: Int, nMin: Int, elementCount: Int): List[Tile] =
    List.tabulate(elementCount)(i => matrix.find(t => t.point == Point(mMax, nMin + i)).get)

  def fourthOps(matrix: List[Tile], mMax: Int, nMax: Int, elementCount: Int): List[Tile] =
    List.tabulate(elementCount)(i => matrix.find(t => t.point == Point(mMax - i, nMax)).get)

  def listToTileMatrix(matrix: List[List[Int]]): List[Tile] = matrix.zipWithIndex.flatMap { row =>
    row._1.zipWithIndex.map(e => Tile(Point(row._2, e._2), e._1))
  }

  private def boardToPath(matrix: List[Tile]): List[Int] =
    matrix
      .sortBy(_.point)(Ordering.by(p => (p.x, p.y)))
      .map(_.element)

  private def printMatrix(list: List[Int], m: Int, n: Int): Unit =
    list
      .grouped(n)
      .foreach(arr => println(arr.mkString(" ")))

  def main(args: Array[String]): Unit = {
    val testValue: List[List[Int]] = List(
      List(1, 2, 3, 4),
      List(7, 8, 9, 10),
      List(13, 14, 15, 16),
      List(19, 20, 21, 22),
      List(25, 26, 27, 28)
    )

    val m        = testValue.size
    val n        = testValue.headOption.map(_.size).getOrElse(0)
    val counter  = math.min(m, n) / 2
    val rotation = 7

    val resultMatrix: List[Tile] =
      (0 until counter).map(i => convertMatrixIntoRing(testValue, i, rotation)).toList.flatten
    printMatrix(boardToPath(resultMatrix), m, n)
  }
}
