
import play.api.Play
import play.api.Play.current

package object constants {
  val Conf = Play.configuration.getObject("instagram.constants").get.unwrapped()
  val AuthorizationUrl = Conf.get("autorithationUrl").toString
  val RedirectUri = Conf.get("redirectUri").toString
  val ClientId = Conf.get("clientId").toString
  val ClientSecret = Conf.get("clientSecret").toString
  val DBUrl = Play.configuration.getString("db.default.url").get
  val DBUser = Play.configuration.getString("db.default.user").get
  val DBDriver = Play.configuration.getString("db.default.driver").get
  val DummyUserId = "5656"
}
