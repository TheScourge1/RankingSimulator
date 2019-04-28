package GenericSlick

import slick.jdbc.JdbcBackend.Database

trait MySQLDB extends DatabaseComponent {

  override val db = Database.forConfig("mySQLDB") //see application.conf
  override val profile = slick.jdbc.MySQLProfile
}
