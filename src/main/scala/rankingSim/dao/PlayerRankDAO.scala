package rankingSim.dao

import rankingSim.models.{PlayerRank, PlayerRanks}

import scala.concurrent.Future

object PlayerRankDAO extends MySQLDB   {
  import profile.api._

  val table = TableQuery[PlayerRanks]

  def getAll :Future[Seq[PlayerRank]] =  {

    table.result.statements.foreach(println)
    db.run(table.result)
  }

  def save(rankBalance: PlayerRank): Future[String] ={
    val insertVal = {table returning table.map(_.playerId) +=rankBalance}
    db.run(insertVal)
  }

  def save(rankBalances: Seq[PlayerRank]): Future[Option[Int]] ={
    val insertBatch: DBIO[Option[Int]] = table ++=rankBalances
    db.run(insertBatch)
  }

}
