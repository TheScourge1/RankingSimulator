package rankingSim.dao

import slick.jdbc.JdbcBackend.Database
import slick.jdbc.JdbcProfile

trait DatabaseComponent {
  val db: Database
  val profile: JdbcProfile
}