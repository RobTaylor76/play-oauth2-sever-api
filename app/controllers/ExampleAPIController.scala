package controllers

import play.api.mvc.{Action, Controller}
import scalaoauth2.provider.OAuth2Provider
import oauth.MyDataHandler

object ExampleAPIController extends Controller with OAuth2Provider {
  def index = Action { implicit request =>
    authorize(new MyDataHandler()) { authInfo =>
      val user = authInfo.user // User is defined on your system
      // access resource for the user
      Ok
    }
  }
}