package controllers

import org.jinstagram.Instagram
import org.jinstagram.auth.InstagramAuthService
import org.jinstagram.auth.model.{Verifier, Token}
import scala.slick.driver.MySQLDriver.simple.Database
import play.api.mvc.{Action, Controller}
import constants._

object OAuther extends Controller {
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
    Ok(views.html.explore())
  }

  val DBIP = Database.forURL(DBUrl, user = DBUser, driver = DBDriver)
}
