package models.oauth2;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
@Table(name = "oauth2_client")
public class Client extends Model{

	public static Finder<Long, Client> find = new Finder<>(Long.class, Client.class);

	@Id
	@Column
	public String id;

	@Column
	public String secret = "";

	@Column
	public String redirect_uri = "";

	@Column
	public String scope = "";
	
	public Client() {}

	public static boolean validate(String clientId, String clientSecret,
			String grantType) {
		// TODO Auto-generated method stub
		return true;
	};
	
	public static Client findById(String id){
		return find.where().eq("id", id).findUnique();
	}

}
/**
   def validate(id: String, secret: String, grantType: String): Boolean = {
    DB.withTransaction { implicit session =>
      val check = for {
        ((c, cgt), gt) <- Clients innerJoin ClientGrantTypes on (_.id === _.clientId) innerJoin GrantTypes on (_._2.grantTypeId === _.id)
        if c.id === id && c.secret === secret && gt.grantType === grantType
      } yield 0
      check.firstOption.isDefined
    }
  }

  def findById(id: String): Option[Client] = {
    DB.withTransaction { implicit session =>
      Clients.filter(c => c.id === id).firstOption
    }
  }

id varchar(80) NOT NULL,
secret varchar(80),
redirect_uri varchar(2000),
scope varchar(2000),*/