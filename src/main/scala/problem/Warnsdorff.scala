package problem

/**
 * Algorithm:
 * Using Warnsdorff Heuristic
 * The knight is moved so that it always proceeds to the square from which the knight will have the fewest onward
 * moves. When calculating the number of onward moves for each candidate square, we do not count moves that
 * revisit any square already visited
 * https://en.wikipedia.org/wiki/Knight%27s_tour#Warnsdorf.27s_rule
 *
 */
case class Tile(x: Int, y: Int)

case class Warnsdorff(
  private val dimension: (Int, Int),
  private val getAllValidMoves: Tile => List[Tile]
) {

  private type Board = Array[Array[Int]]

  private def isVisited(tile: Tile, board: Board): Boolean = board(tile.x)(tile.y) != -1

  private def visit(tile: Tile, board: Board, step: Int): Board = {
    board(tile.x)(tile.y) = step
    board
  }

  private def getNeighbours(tile: Tile, board: Board): List[Tile] =
    getAllValidMoves(tile)
      .filter(t => !isVisited(t, board))

  private def weight(tile: Tile, board: Board): Int = getNeighbours(tile, board).length

  private def nextMove(tile: Tile, board: Board): Option[Tile] =
    getNeighbours(tile, board)
      .map(t => t -> weight(t, board))
      .minByOption(_._2)
      .map(_._1)

  private def loop(startingTile: Tile, board: Board, currentStep: Int): Option[Board] = {
    if (currentStep == (dimension._1 * dimension._2))
      return Option(visit(startingTile, board, currentStep))

    nextMove(startingTile, board) match {
      case Some(tile) => loop(tile, visit(startingTile, board, currentStep), currentStep + 1)
      case None       => None
    }
  }

  def getPath(statingTile: Tile): Either[String, Board] = {
    val board: Board = Array.tabulate(dimension._1, dimension._2)((_, _) => -1)
    loop(statingTile, board, 1) match {
      case None       => Left("No Path Found")
      case Some(tile) => Right(tile)
    }
  }

}

object Warnsdorff extends App {
  private val (n, m) = (10, 10)

  private def validMove(tile: Tile): Boolean = tile.x >= 0 && tile.x < n && tile.y >= 0 && tile.y < m

  private def verticalMoves(tile: Tile): List[Tile] = Tile(tile.x, tile.y + 3) :: Tile(tile.x, tile.y - 3) :: Nil

  private def horizontalMoves(tile: Tile): List[Tile] = Tile(tile.x + 3, tile.y) :: Tile(tile.x - 3, tile.y) :: Nil

  private def diagonalMoves(tile: Tile): List[Tile] =
    Tile(tile.x + 2, tile.y + 2) :: Tile(tile.x - 2, tile.y - 2) :: Tile(tile.x + 2, tile.y - 2) :: Tile(
      tile.x - 2,
      tile.y + 2
    ) :: Nil

  private def createAllDirectionPossibleMoves(tile: Tile): List[Tile] =
    verticalMoves(tile) ++ horizontalMoves(tile) ++ diagonalMoves(tile)

  private def createAllValidMove(tile: Tile): List[Tile] =
    createAllDirectionPossibleMoves(tile)
      .filter(validMove)

  private def printBoard(checkerBoard: Array[Array[Int]]): Unit = {
    checkerBoard.foreach(arr => println(arr.mkString(",")))
  }

  private def boardToPath(board: Array[Array[Int]]): List[Tile] =
    board.toList
      .map(_.toList)
      .zipWithIndex
      .flatMap(
        in =>
          in._1.zipWithIndex
            .map(e => (Tile(in._2, e._2), e._1))
      )
      .sortBy(_._2)
      .map(_._1)

  Warnsdorff((n, m), createAllValidMove)
    .getPath(Tile(0, 0)) match {
    case Right(value) => printBoard(value)
    case Left(value)  => println(value)
  }

}
