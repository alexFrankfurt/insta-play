package controllers.insta

import com.google.inject.Inject
import constants.AppConstants
import controllers.OAuther
import play.api.mvc.{Action, Controller}
import twirlc._

import scala.collection.JavaConversions.asScalaBuffer

class Services @Inject() (oAuther: OAuther, ac: AppConstants) extends Controller{
  import oAuther.{instagram, auther}
  import ac._

  var minId = 0
  var maxId = 10
  val count = 10

  def gallery = Action {
    if (!auther){
      Ok(views.html.basic.error.render())
    }
    val mediaFeed = instagram.getUserFeeds(null, null, 20).getData
    Ok(CommonPage("Gallery")(
       styles = MainStyle(),
       contents = GalleryPage(mediaFeed)))
  }

  def profile = Action {
    if (!auther){
      Ok(views.html.basic.error.render())
    }
    var userInfo = instagram.getCurrentUserInfo()
    var userInfoData = userInfo.getData()

    var mediaFeed = instagram.getRecentMediaFeed(userInfo.getData.getId)
    var mediaFeeds = mediaFeed.getData()
    Ok(CommonPage("Gallery")(
      styles = MainStyle(),
      contents = ProfilePage(userInfoData, mediaFeeds)))
  }

  def load = Action {
    if (!auther){
      Ok(views.html.basic.error.render())
    }
    val mediaFeed = instagram.getUserFeeds((maxId += 100).toString, (minId += 100).toString, count)
    Ok(views.html.basic.additionalPhoto(asScalaBuffer(mediaFeed.getData)))
  }

  def tagSearch = Action {
    if (!auther){
      Ok(views.html.basic.error.render())
    }
    Ok(CommonPage("Find photos!")(
       styles = MainStyle(),
       scripts = TagSearchScript(),
       contents = Content(Navigation(), TagForm())))
  }

  def tagSearchSpecific(tag: String) = Action {
    if (!auther){
      Ok(views.html.basic.error.render())
    }
    val data = instagram.getRecentMediaTags(tag).getData
    Ok(AdditionalPhoto(data))
  }
}
