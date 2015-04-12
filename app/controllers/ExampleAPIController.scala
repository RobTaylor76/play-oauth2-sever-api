package controllers

import play.api.libs.json.JsString
import play.api.mvc.{Action, Controller}
import scalaoauth2.provider.OAuth2Provider
import oauth.{OauthAuthentication, MyDataHandler}

object ExampleAPIController extends Controller with OAuth2Provider {
  def index = Action { implicit request =>
    authorize(new MyDataHandler()) { authInfo =>
      val user = authInfo.user // User is defined on your system
      // access resource for the user
      Ok
    }
  }

  def oauth = OauthAuthentication { implicit request =>
    Ok(JsString("boo!"))
  }
}