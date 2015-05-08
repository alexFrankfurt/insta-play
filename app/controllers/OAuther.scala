package controllers

import javax.inject.{Singleton, Inject}

import org.jinstagram.Instagram
import org.jinstagram.auth.InstagramAuthService
import org.jinstagram.auth.model.{Verifier, Token}
import scala.slick.driver.MySQLDriver.simple.Database
import play.api.mvc.{Action, Controller}
import constants.AppConstants

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
    Redirect(HomePage)
  }

  val DBIP = Database.forURL(DBUrl, user = DBUser, driver = DBDriver)
}
