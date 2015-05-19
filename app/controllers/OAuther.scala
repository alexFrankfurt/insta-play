package controllers

import javax.inject.{Singleton, Inject}

import org.jinstagram.Instagram
import org.jinstagram.auth.InstagramAuthService
import org.jinstagram.auth.model.{Verifier, Token}
import org.jinstagram.entity.tags.TagMediaFeed
import org.jinstagram.entity.users.feed.MediaFeedData
import slick.driver.MySQLDriver.api.Database
import play.api.mvc.{Action, Controller}
import constants.AppConstants

import scala.collection.JavaConversions.asScalaBuffer

@Singleton
class OAuther @Inject() (consts: AppConstants) extends Controller {
  import consts._

  val service = new InstagramAuthService()
                    .apiKey(ClientId)
                    .apiSecret(ClientSecret)
                    .callback(RedirectUri)
                    .build()

  val emptyToken: Token = null

  //val authorizationUrl = service.getAuthorizationUrl(emptyToken)

  var instagram = new Instagram(emptyToken)
  var auther : Boolean = false

  def createInsta(code: String) = Action {
    val verifier = new Verifier(code)
    val accessToken = service.getAccessToken(emptyToken, verifier)
    instagram = new Instagram(accessToken)
    auther = true
    Redirect(GalleryPath)
  }

  def logout = Action {
    instagram = null;
    auther = false;
    Redirect("/")
  }

//  val DBIP = Database.forURL(DBUrl, user = DBUser, driver = DBDriver)
}

