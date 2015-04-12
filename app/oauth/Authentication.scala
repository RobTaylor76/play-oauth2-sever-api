package oauth

import models.oauth2.User
import play.api.mvc.Security.{AuthenticatedBuilder, AuthenticatedRequest}
import play.api.mvc.{Result, ActionBuilder, WrappedRequest, Request}

import scala.concurrent.Future
import scalaoauth2.provider._

/**
 * Created by rob on 11/04/15.
 */

import scala.concurrent.ExecutionContext.Implicits.global

class OauthAuthentication[A](val authInfo: AuthInfo[User],
                                request: Request[A]) extends WrappedRequest[A](request)

object OauthAuthentication extends ActionBuilder[OauthAuthentication]  with OAuth2Provider {

  def invokeBlock[A](request: Request[A], block: (OauthAuthentication[A]) => Future[Result]) = {
 //   val user = Some(new User())

//    Future {
//      authorize(new MyDataHandler) {
//        authInfo => block(new OauthAuthentication[A](authInfo, request))
//      }
//    }

    ProtectedResource.handleRequest(request, new MyDataHandler()) match {
      case Left(e) if e.statusCode == 400 => Future(BadRequest.withHeaders(responseOAuthErrorHeader(e)))
      case Left(e) if e.statusCode == 401 => Future(Unauthorized.withHeaders(responseOAuthErrorHeader(e)))
      case Right(authInfo) => block(new OauthAuthentication[A](authInfo, request))
    }


  //  AuthenticatedBuilder(req => user).authenticate(request,
  //  { authRequest: AuthenticatedRequest[A, User] =>
  //     block(new OauthAuthentication[A](authRequest.user, request))
  //  })
  }
}