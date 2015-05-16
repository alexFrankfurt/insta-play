package controllers

import javax.inject.Inject

import constants.AppConstants
import models.slick.Users
import play.api.mvc.{Action, Controller}
import play.twirl.api.Html
import twirlc.SSeq

import scala.collection.JavaConverters._
import slick.driver.MySQLDriver.api._
import concurrent.ExecutionContext.Implicits.global
import concurrent.Await
import scala.concurrent.duration.Duration

class Admin @Inject() (ac: AppConstants, oa: OAuther) extends Controller{
  import ac._
  import oa._
  def mainPage = Action {
//    val db = DBIP
//    val users = TableQuery[Users]
//    val resu = for (u <- users) yield u
//    val resf = resu.result
//    val res = Await.result(db.run(resf), Duration.Inf)

//    val feedTag = instagram.getRecentMediaTags("medvedev", 2).getData.asScala.toList.head
//    val feed = instagram.getUserLikes(DummyUserId).toString

    Ok(CommonPage("Admin page")(
      styles = SSeq(Favicon(), MainStyle()),
      contents = SSeq(Content(Navigation()))))
  }
  
  def test = Action {
    Ok(Html("""<a href="https://api.instagram.com/oauth/authorize/?client_id=41631eeee0f648e689d6bafbf8b2413e&redirect_uri=http://localhost:2904/handleInstagramToken&response_type=code&scope=likes">click</a>"""))
  }
}
