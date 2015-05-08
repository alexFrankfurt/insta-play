package controllers

import javax.inject.Inject

import constants.AppConstants
import models.slick.Users
import play.api.mvc.{Action, Controller}
import play.twirl.api.Html

import scala.collection.JavaConverters._
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

    val feedTag = instagram.getRecentMediaTags("like4like", 2).getData.asScala.toList.head
    val feed = instagram.getUserLikes(DummyUserId).toString

    Ok(views.html.homepage.main(feedTag.toString))
  }
  
  def test = Action {

    Ok(Html("""<a href="https://api.instagram.com/oauth/authorize/?client_id=41631eeee0f648e689d6bafbf8b2413e&redirect_uri=http://localhost:2904/handleInstagramToken&response_type=code&scope=likes">click</a>"""))
  }
}
