package GenericSlick

import slick.jdbc.MySQLProfile.api.TableQuery

import scala.concurrent.Future

abstract class BaseRepo[T<: BaseTable[E], E<: BaseEntity](clazz: TableQuery[T]) extends MySQLDB{
  import profile.api._
  val query:TableQuery[T] = clazz

  def getAll :Future[Seq[E]] = {
    db.run(query.result)
  }

  def save(model: E): Future[Int] ={
    val insertVal = {query returning query.map(_.id) +=model}
    db.run(insertVal)
  }

  def save(models: Seq[E]): Future[Option[Int]] ={
    val insertBatch: DBIO[Option[Int]] = query ++=models
    db.run(insertBatch)
  }

  def update(model: E) : Future[Int] = {
    val updateBatch = query.insertOrUpdate(model)
    db.run(updateBatch)
  }

  def delete(model: E): Future[Int] = {
    delete(model.id)
  }

  def delete(id: Int): Future[Int] = {
    val q = query.filter(_.id === id)
    val action = q.delete
    db.run(action)
  }

}
