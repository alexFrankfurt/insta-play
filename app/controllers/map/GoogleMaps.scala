package controllers.map

import javax.inject.Inject

import constants.AppConstants
import play.api.mvc.{Controller, Action}

class GoogleMaps @Inject() (ac: AppConstants) extends Controller {
  import ac._
  def map = Action {
    Ok(MapPage())
  }

  def mapDart = Action {
    Ok(views.html.map.mapdart())
  }
}
