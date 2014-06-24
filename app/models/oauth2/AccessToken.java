package models.oauth2;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

import scala.Option;
import play.db.ebean.Model;

@Entity
@Table(name = "oauth2_access_token")
public class AccessToken extends Model{

  public static Finder<Long, AccessToken> find = new Finder<>(Long.class, AccessToken.class);

  @Id
  @Column
  public String access_token;

  @Column
  public String refresh_token;

  @Column 
  public Long user_id;
  
  @Column
  public String client_id;
  
  @Column
  public String scope;
  
  @Column 
  public Integer expires_in;

  @Column
  public Timestamp created_at;
  
  public AccessToken() {};

    public AccessToken(String access_token, Option<String> refresh_token, Long user_id, Option<String> scope, int accessTokenExpiresIn, java.sql.Timestamp createdAt, String client_id) {
      this.access_token = access_token;
      if (refresh_token.nonEmpty()){
        this.refresh_token = refresh_token.get();
      }
      this.user_id = user_id;
      if (scope.nonEmpty()){
        this.scope = scope.get();
      }
      this.expires_in = accessTokenExpiresIn;
      this.created_at = createdAt;
      this.client_id = client_id;

    }

    public static AccessToken findAccessToken(String id) {
    return find.where().eq("access_token", id).findUnique();
  }
  
  public static AccessToken findToken(Long userId, String clientId) {
    return  find.where().eq("client_id", clientId)
        .eq("user_id", userId).findUnique();
  }
  
  public static AccessToken findRefreshToken(String token) {
    return find.where().eq("refresh_token", token).findUnique();
  }
  
  public static void deleteExistingAndCreate(AccessToken accessToken, 
                        Long userId,
                        String clientId) {
    List<AccessToken> tokens = find.where().eq("client_id", clientId).eq("user_id", userId).findList();
    for(AccessToken oldToken : tokens) {
      oldToken.delete();
    }
    create(accessToken);
  }
  
  public static AccessToken create(AccessToken token) {
    token.save();
    return token;
  }

}
/**
   def create(accessToken: AccessToken) = {
    DB.withTransaction { implicit session =>
      AccessTokens.insert(accessToken)
    }
  }

  def deleteExistingAndCreate(accessToken: AccessToken, userId: Long, clientId: String) = {
    DB.withTransaction { implicit session =>
      // these two operations should happen inside a transaction
      AccessTokens.where(a => a.clientId === clientId && a.userId === userId).delete
      AccessTokens.insert(accessToken)
    }
  }

  def findToken(userId: Long, clientId: String): Option[AccessToken] = {
    DB.withTransaction { implicit session =>
      AccessTokens.where(a => a.clientId === clientId && a.userId === userId).firstOption
    }
  }

  def findAccessToken(token: String): Option[AccessToken] = {
    DB.withTransaction { implicit session =>
      AccessTokens.where(a => a.accessToken === token).firstOption
    }
  }

  def findRefreshToken(token: String): Option[AccessToken] = {
    DB.withTransaction { implicit session =>
      AccessTokens.where(a => a.refreshToken === token).firstOption
    }
  }
 
  access_token varchar(60) NOT NULL,
  refresh_token varchar(60),
  user_id integer NOT NULL,
  client_id varchar(80) NOT NULL,
  scope varchar(2000),
  expires_in integer NOT NULL,*/

