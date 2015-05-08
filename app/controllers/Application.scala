package controllers

import javax.inject.Inject

import constants.AppConstants
import play.api.mvc.{Action, Controller}


class Application @Inject() (ac: AppConstants) extends Controller{
  import ac.{AuthorizationUrl, Scopes}
  import Scopes._

  def index = Action {
    Ok(views.html.index(AuthorizationUrl, Likes, Comments, Relationships))
  }
}
