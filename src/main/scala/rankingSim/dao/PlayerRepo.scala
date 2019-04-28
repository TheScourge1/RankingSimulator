package rankingSim.dao

import GenericSlick.BaseRepo
import rankingSim.models.{Player, PlayerTable}
import slick.lifted.TableQuery

class PlayerRepo extends BaseRepo[PlayerTable,Player](TableQuery[PlayerTable])   {
/*  import this.profile.api._

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
  }*/

}
