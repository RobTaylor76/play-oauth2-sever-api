package oauth

import scalaoauth2.provider.{AuthInfo, DataHandler}
import java.util.Date
import java.sql.Timestamp
import util.Crypto
import models.oauth2._

class MyDataHandler extends DataHandler[models.oauth2.User] {
  def validateClient(client_id: String, clientSecret: String, grantType: String): Boolean = {
    Client.validate(client_id, clientSecret, grantType)
  }

  def findUser(username: String, password: String): Option[User] = {
    Option(User.findUser(username, password))
  }

  def createAccessToken(authInfo: AuthInfo[User]): scalaoauth2.provider.AccessToken = {
    val access_tokenExpiresIn = 60 * 60 // 1 hour
    val now = new Date()
    val createdAt = new Timestamp(now.getTime)
    val refresh_token = Some(Crypto.generateToken())
    val access_token = Crypto.generateToken()

    val tokenObject = new models.oauth2.AccessToken(access_token, refresh_token, authInfo.user.id.toInt, authInfo.scope, access_tokenExpiresIn, createdAt, authInfo.clientId)
    AccessToken.deleteExistingAndCreate(tokenObject, authInfo.user.id.toInt, authInfo.clientId)
    scalaoauth2.provider.AccessToken(access_token, refresh_token, authInfo.scope, Some(access_tokenExpiresIn.toLong), now)
  }

  def getStoredAccessToken(authInfo: AuthInfo[User]): Option[scalaoauth2.provider.AccessToken] = {
    Option(AccessToken.findToken(authInfo.user.id, authInfo.clientId)) map { a =>
      scalaoauth2.provider.AccessToken(a.access_token, Option(a.refresh_token), Option(a.scope), Some(a.expires_in.toLong), a.created_at)
    }
  }

  def refreshAccessToken(authInfo: AuthInfo[User], refresh_token: String): scalaoauth2.provider.AccessToken = {
    createAccessToken(authInfo)
  }

  def findClientUser(client_id: String, clientSecret: String, scope: Option[String]): Option[User] = {
    None // Not implemented yet
  }

  def findAccessToken(token: String): Option[scalaoauth2.provider.AccessToken] = {
    Option(AccessToken.findAccessToken(token)) map { a =>
      scalaoauth2.provider.AccessToken(a.access_token, Option(a.refresh_token), Option(a.scope), Some(a.expires_in.toLong), a.created_at)
    }
  }

  def findAuthInfoByAccessToken(access_token: scalaoauth2.provider.AccessToken): Option[AuthInfo[User]] = {
    Option(models.oauth2.AccessToken.findAccessToken(access_token.token)) map { a =>
      val user = Option(models.oauth2.User.getById(a.user_id)).get
      AuthInfo(user, a.client_id, Option(a.scope), Some(""))
    }
  }

  def findAuthInfoByRefreshToken(refresh_token: String): Option[AuthInfo[User]] = {
    Option(models.oauth2.AccessToken.findRefreshToken(refresh_token)) map { a =>
      val user = Option(models.oauth2.User.getById(a.user_id)).get
      AuthInfo(user, a.client_id, Option(a.scope), Some(""))
    }
  }

  def findAuthInfoByCode(code: String): Option[AuthInfo[User]] = {
    Option(models.oauth2.AuthCode.find(code)) map { a =>
      val user = Option(models.oauth2.User.getById(a.user_id)).get
      AuthInfo(user, a.client_id, Option(a.scope), Option(a.redirect_uri))
    }
  }
}
