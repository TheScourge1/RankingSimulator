package rankingSim.dao

import rankingSim.models.{RankingPoint, RankingPoints}

import scala.concurrent.Future

object RankingPointsDAO extends MySQLDB   {
  import profile.api._

  val table = TableQuery[RankingPoints]

  def getAll :Future[Seq[RankingPoint]] =  {

    table.result.statements.foreach(println)
    db.run(table.result)
  }

  def save(rankingPoint: RankingPoint): Future[Int] ={
    val insertVal = {table returning table.map(_.id) +=rankingPoint}
    db.run(insertVal)
  }

  def save(rankingPoints: Seq[RankingPoint]): Future[Option[Int]] ={
    val insertBatch: DBIO[Option[Int]] = table ++=rankingPoints
    db.run(insertBatch)
  }

  def delete(id: Int): Future[Int] = {
    val q = table.filter(_.id === id)
    val action = q.delete
    db.run(action)
  }

}
