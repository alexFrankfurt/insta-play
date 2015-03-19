package controllers

import play.api.mvc.{Action, Controller}
import constants._

object Applics extends Controller{
  def some = Action {
    Ok(s"some $RedirectUri <br> $ClientId <br> $ClientSecret")
  }
}
