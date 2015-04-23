package controllers

import org.jinstagram.Instagram
import org.jinstagram.auth.InstagramAuthService
import org.jinstagram.auth.model.{Verifier, Token}
import play.api.mvc.{Action, Controller}
import constants._

object OAuther extends Controller {
  val authorizationUrl = AuthorizationUrl
  val servise = new InstagramAuthService()
                    .apiKey(ClientId)
                    .apiSecret(ClientSecret)
                    .callback(RedirectUri)
                    .build()

  val emptyToken: Token = null
  var instagram = new Instagram(emptyToken)

  def createInsta(code: String) = Action {
    val verifier = new Verifier(code)
    val accessToken = servise.getAccessToken(emptyToken, verifier)
    instagram = new Instagram(accessToken)
    Ok(views.html.explore())
  }
}
