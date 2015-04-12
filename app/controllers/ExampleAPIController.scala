package controllers

import play.api.libs.json.JsString
import play.api.mvc.{Action, Controller}
import scalaoauth2.provider.OAuth2Provider
import oauth.{OauthAuthentication, MyDataHandler}

object ExampleAPIController extends Controller with OAuth2Provider {
  def index = OauthAuthentication { request =>
      val authInfo = request.authInfo // User is defined on your system
      // access resource for the user
      Ok(JsString(authInfo.user.username))
  }

  def oauth = OauthAuthentication { implicit request =>
    Ok(JsString("boo!"))
  }
}