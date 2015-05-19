package constants

import javax.inject.Singleton

import org.jinstagram.auth.model.Token
import play.api.Play
import play.api.Play.current
import play.twirl.api.BaseScalaTemplate

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

  // Styles
  val MainStyle = views.html.style.main
  val Favicon = views.html.style.favicon
  val Stylesheet = views.html.style.stylesheet
  val StyleMedia = views.html.style.media
  val GalleryFonts = views.html.style.fonts

  // Scripts
  val ScrollScript = views.html.script.scroll
  val TagSearchScript = views.html.script.tagSearch
  val GalleryScripts = views.html.script.gallery

  val Content = views.html.style.content

  val CommonPage = views.html.common
  val IndexPage = views.html.index

  val HomePage = views.html.homepage.main
  val HomePath = "/home"

  val Navigation = views.html.homepage.nav
  val TagForm = views.html.tagForm

  val MapPage = views.html.map.show
  val AdditionalPhoto = views.html.basic.additionalPhoto

  val ProfilePage = views.html.basic.profile
  val ProfilePath = "/profile"

  val GalleryPage = views.html.page.gallery
  val GalleryPath = "/gallery"

  val PopularPath = "/popular"
  val SearchPhotoByTag = "/inputTag"
  val Location = "/location"

  val DummyUserId = "5656"

  var token: Token = _
}
