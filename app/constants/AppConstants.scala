package constants

import javax.inject.Singleton

import com.typesafe.config.ConfigObject
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
  val ScopeLikes = Scope.getString("likes").get
  val ScopeComments = Scope.getString("comments").get
  val ScopeRelationships = Scope.getString("relationships").get

  val HomePage = "/home"
  val ProfilePage = "/profile"
  val GalaryPage = "/galary"

  val DummyUserId = "5656"
}
