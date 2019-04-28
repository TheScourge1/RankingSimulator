package GenericSlick

import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

import scala.reflect.ClassTag

trait BaseEntity {
  val id: Int
}

abstract class BaseTable[E: ClassTag](tag: Tag, schemaName: Option[String], tableName: String)
  extends Table[E](tag, schemaName, tableName) {
 // val classOfEntity = classTag[E].runtimeClass
  val id: Rep[Int] = column[Int]("Id", O.PrimaryKey, O.AutoInc)
}