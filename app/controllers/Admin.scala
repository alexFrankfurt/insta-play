package controllers

import javax.inject.Inject

import models.slick.Users
import play.api.mvc.{Action, Controller}
import scala.slick.driver.MySQLDriver.simple._
import constants.AppConstants

import scala.slick.lifted.TableQuery

class Admin @Inject() (ca: AppConstants, oa: OAuther) extends Controller{
  import ca._
  import oa._
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
