package rankingSim.dao

import rankingSim.models.{Player, Players}

import scala.concurrent.Future

object PlayersDAO extends MySQLDB   {
  import this.profile.api._

  val table = TableQuery[Players]

  def getAll :Future[Seq[Player]] =  {

    table.result.statements.foreach(println)
    db.run(table.result)
  }

  def save(player: Player): Future[Int] ={
    val insertVal = {table returning table.map(_.id) +=player}
    db.run(insertVal)
  }

  def save(players: Seq[Player]): Future[Option[Int]] ={
    val insertBatch: DBIO[Option[Int]] = table ++=players
    db.run(insertBatch)
  }

}
