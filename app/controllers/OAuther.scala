package controllers

import javax.inject.{Singleton, Inject}

import org.jinstagram.Instagram
import org.jinstagram.auth.InstagramAuthService
import org.jinstagram.auth.model.{Verifier, Token}
import scala.slick.driver.MySQLDriver.simple.Database
import play.api.mvc.{Action, Controller}
import constants.App

@Singleton
class OAuther @Inject() (consts: App) extends Controller {
  import consts._

  val service = new InstagramAuthService()
                    .apiKey(ClientId)
                    .apiSecret(ClientSecret)
                    .callback(RedirectUri)
                    .build()

  val emptyToken: Token = null

  var instagram = new Instagram(emptyToken)

  def createInsta(code: String) = Action {
    val verifier = new Verifier(code)
    val accessToken = service.getAccessToken(emptyToken, verifier)
    instagram = new Instagram(accessToken)
    Redirect("/home")
  }

  val DBIP = Database.forURL(DBUrl, user = DBUser, driver = DBDriver)
}