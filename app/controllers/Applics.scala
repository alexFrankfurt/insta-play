package controllers

import play.api.mvc.{Action, Controller}

object Applics extends Controller{
  def some = Action {
    Ok("some")
  }

  def another = Action {
    Ok("another")
  }

}
