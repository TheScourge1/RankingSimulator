package rankingSim.dao

import GenericSlick.{BaseRepo}
import rankingSim.models.{InputMatch, InputMatchTable}
import slick.lifted.TableQuery

import scala.concurrent.Future

class InputMatchRepo extends BaseRepo[InputMatchTable,InputMatch](TableQuery[InputMatchTable])   {
  import profile.api._

  def getAllHE :Future[Seq[InputMatch]] =  {
    val action = query.filter(game => game.matchTypeid === "HE")
    db.run(query.result)
  }

}
