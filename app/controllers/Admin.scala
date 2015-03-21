package controllers

import play.api.mvc.{Action, Controller}
import Explorer.instagram

object Admin extends Controller{
  def adminPage = Action {
    val feed = instagram.getUserLikes("5656")
    val uList = feed.getUserList.toString
    Ok(views.html.admin.main(uList))
  }
}
