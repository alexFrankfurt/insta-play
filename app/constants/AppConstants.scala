package constants

import javax.inject.Singleton

import com.typesafe.config.ConfigObject
import org.jinstagram.auth.model.Token
import play.api.Play
import play.api.Play.current

@Singleton
class AppConstants {
  val Conf = Play.configuration.getConfig("constants.app").get

  val RedirectUri = Conf.getString("redirectUri").get
  val ClientId = Conf.getString("clientId").get
  val ClientSecret = Conf.getString("clientSecret").get

  val AuthorizationUrl = Conf.getString("authorizationUrl").get

  val DBUrl = Play.configuration.getString("db.default.url").get
  val DBUser = Play.configuration.getString("db.default.username").get
  val DBDriver = Play.configuration.getString("db.default.driver").get

  val Scope = Conf.getConfig("scope").get
  object Scopes {
    val Likes = Scope.getString("likes").get
    val Comments = Scope.getString("comments").get
    val Relationships = Scope.getString("relationships").get
  }

  val IndexPage = views.html.index

  val HomePage = views.html.homepage.main
  val HomePath = "/home"

  val MapPage = views.html.map.show

  val ProfilePath = "/profile"

  val GalleryPage = views.html.basic.gallery
  val GalleryPath = "/gallery"

  val PopularPath = "/popular"
  val SearchPhotoByTag = "/inputTag"
  val Location = "/location"

  val DummyUserId = "5656"

  var token: Token = _
}
