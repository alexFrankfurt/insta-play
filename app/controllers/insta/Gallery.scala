package controllers.insta

import com.google.inject.Inject
import constants.AppConstants
import controllers.OAuther
import play.api.mvc.{Action, Controller}
import twirlc._

import scala.collection.JavaConversions.asScalaBuffer

class Gallery @Inject() (oAuther: OAuther, ac: AppConstants) extends Controller{
  import oAuther.instagram
  import ac._

  var minId = 0
  var maxId = 10
  val count = 10

  def load = Action {
    val mediaFeed = instagram.getUserFeeds((maxId += 100: Unit).toString, (minId += 100: Unit).toString, count)
    Ok(views.html.basic.additionalPhoto(asScalaBuffer(mediaFeed.getData)))
  }

  def gallery = Action {
    val mediaFeed = instagram.getUserFeeds(null, null, 20).getData
    Ok(
      CommonPage("Feed")(
        styles = MainStyle(),
        scripts = ScrollScript(),
        contents = Content(Navigation(), GalleryPage("Your feed: ", mediaFeed))
      )
    )
  }
}
