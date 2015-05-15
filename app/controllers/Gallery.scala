package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}
import scala.collection.JavaConversions.asScalaBuffer

class Gallery @Inject() (oAuther: OAuther) extends Controller{
  import oAuther.instagram

  var minId = 0
  var maxId = 100
  val count = 100

  def load = Action {
    val mediaFeed = instagram.getUserFeeds((maxId += 100: Unit).toString, (minId += 100: Unit).toString, count)
    Ok(views.html.basic.additionalPhoto(asScalaBuffer(mediaFeed.getData)))
  }
}
