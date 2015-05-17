package controllers

import javax.inject.Inject

import constants.AppConstants
import play.api.mvc.{Result, Action, Controller}


class Application @Inject() (ac: AppConstants) extends Controller{
  import ac.{AuthorizationUrl, Scopes, IndexPage}
  import Scopes._

  def index = Action {
    Ok(IndexPage(AuthorizationUrl, Likes, Comments, Relationships))
  }

}
