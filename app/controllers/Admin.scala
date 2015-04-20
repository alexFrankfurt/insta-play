package controllers

import models.slick.Users
import play.api.mvc.{Action, Controller}
import scala.slick.driver.MySQLDriver.simple._
import constants.DummyUserId
import OAuther.instagram
import OAuther.DBIP

import scala.slick.lifted.TableQuery

object Admin extends Controller{
  def adminPage = Action {
    val users = TableQuery[Users]
    val usersR = DBIP withSession { implicit session =>
      users.run
    }
    val feed = instagram.getUserLikes(DummyUserId)
    val uList = feed.getUserList.toString
    Ok(views.html.homepage.main(usersR.toString))
  }
}
