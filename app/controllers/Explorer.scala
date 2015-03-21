package controllers

import org.jinstagram.Instagram
import org.jinstagram.auth.InstagramAuthService
import org.jinstagram.auth.model.{Verifier, Token}
import play.api.mvc.{Action, Controller}
import constants._

object Explorer extends Controller{
  def some = Action {
    Ok(s"some $RedirectUri <br> $ClientId <br> $ClientSecret")
  }

  val servise = new InstagramAuthService()
                    .apiKey(ClientId)
                    .apiSecret(ClientSecret)
                    .callback(RedirectUri)
                    .build()

  val emptyToken: Token = null
  var instagram = new Instagram(emptyToken)

  def showInsta(code: String) = Action {
    val verifier = new Verifier(code)
    val accessToken = servise.getAccessToken(emptyToken, verifier)
    instagram = new Instagram(accessToken)
    val mediaId= 555.toString
    val feed = instagram.getUserLikes(mediaId)
    val listUsers = feed.getUserList
    Ok(views.html.explore())
  }

  def showMediaFeed(mediaId: String = "5656") = Action {
    val feed = instagram.getUserLikes(mediaId)
    val list = feed.getUserList
    Ok(views.html.media(list.toString))
  }
}
