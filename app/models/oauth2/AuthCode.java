package models.oauth2;

import javax.persistence.*;

import scala.Option;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
@Table(name = "oauth2_auth_code")
public class AuthCode extends Model {

	public static Finder<Long, AuthCode> find = new Finder<>(Long.class, AuthCode.class);

	@Id
	@Column
	public String authorization_code;

	@Column
	public String redirect_uri = "";

	@Column
	public String scope;
	
	@Column
	public String client_id;
	
	@Column 
	public Long expires_in;

	@Column 
	public Long user_id;
	
	public AuthCode() {}
	
	public static AuthCode find(String code) {
		return find.where().eq("authorization_code", code).findUnique();
		//TODO need to filter out expired tokens....
		
	}
	
} 
/**
 * 
 * object AuthCodes extends DAO {
  def find(code: String) = {
    DB.withTransaction { implicit session =>
      val authCode = AuthCodes.where(a => a.authorizationCode === code).firstOption

      // filtering out expired authorization codes
      authCode.filter(p => p.createdAt.getTime + (p.expiresIn * 1000) > new Date().getTime)
    }
  }

 * 

  authorization_code varchar(40) NOT NULL,
  user_id integer NOT NULL,
  redirect_uri varchar(2000),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  scope varchar(1000),
  client_id varchar(80) NOT NULL,
  expires_in integer NOT NULL,*/
