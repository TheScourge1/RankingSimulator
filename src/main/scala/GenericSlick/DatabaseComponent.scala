package GenericSlick

import slick.jdbc.JdbcBackend.Database
import slick.jdbc.JdbcProfile

//books.underscore.io/essential-slick/
trait DatabaseComponent {
  val db: Database
  val profile: JdbcProfile
}
