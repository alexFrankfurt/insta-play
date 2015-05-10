package controllers

import javax.inject.{Singleton, Inject}

import org.jinstagram.Instagram
import org.jinstagram.auth.InstagramAuthService
import org.jinstagram.auth.model.{Verifier, Token}
import org.jinstagram.entity.tags.TagMediaFeed
import org.jinstagram.entity.users.feed.MediaFeedData
import scala.slick.driver.MySQLDriver.simple.Database
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
                    .scope(Scopes.Likes)
                    .build()

  val emptyToken: Token = null

  var instagram = new Instagram(emptyToken)

  def createInsta(code: String) = Action {
    val verifier = new Verifier(code)
    val accessToken = service.getAccessToken(new Token(code, ClientSecret), verifier)
    instagram = new Instagram(accessToken)
    Redirect(HomePath)
  }

  val DBIP = Database.forURL(DBUrl, user = DBUser, driver = DBDriver)
}
