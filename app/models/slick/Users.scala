package models.slick

import slick.driver.MySQLDriver.api._

class Users(tag: Tag) extends Table[(String, String, String, Boolean)](tag, "Users"){
  def login = column[String]("login", O.PrimaryKey)
  def email = column[String]("email")
  def password = column[String]("password")
  def isAdmin = column[Boolean]("is_admin")
  def * = (login, email, password, isAdmin)
}
