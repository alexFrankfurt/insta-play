
import play.api.Play
import play.api.Play.current

package object constants {
  val Conf = Play.configuration.getObject("instagram.constants").get.unwrapped()
  val RedirectUri = Conf.get("redirectUri").toString
  val ClientId = Conf.get("clientId").toString
  val ClientSecret = Conf.get("clientSecret").toString
}
