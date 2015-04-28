package controllers

import javax.inject.Inject

import constants.AppConstants
import models.slick.Users
import org.jinstagram.entity.users.feed.MediaFeedData
import play.api.mvc.{Action, Controller}

import scala.collection.{mutable, JavaConversions}
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

class Admin @Inject() (ac: AppConstants, oa: OAuther) extends Controller{
  import ac._
  import oa._
  def adminPage = Action {
    val users = TableQuery[Users]
    val usersR = DBIP withSession { implicit session =>
      users.run
    }

    val feedTag = JavaConversions.iterableAsScalaIterable(instagram.getRecentMediaTags("like4like", 2).getData)
    val feed = instagram.getUserLikes(DummyUserId).toString
    Ok(views.html.homepage.main(feedTag.toString))
  }
  
  def test = Action {
    Ok(views.html.index(AuthorizationUrl, ScopeLikes))
  }
}
