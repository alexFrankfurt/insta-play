package constants

import javax.inject.Singleton

import play.api.Play
import play.api.Play.current

@Singleton
class App {
  val Conf = Play.configuration.getObject("constants.app").get.unwrapped()

  val RedirectUri = Conf.get("redirectUri").toString
  val ClientId = Conf.get("clientId").toString
  val ClientSecret = Conf.get("clientSecret").toString

  val AuthorizationUrl = Conf.get("authorizationUrl").toString

  val DBUrl = Play.configuration.getString("db.default.url").get
  val DBUser = Play.configuration.getString("db.default.username").get
  val DBDriver = Play.configuration.getString("db.default.driver").get

  val DummyUserId = "5656"
}
