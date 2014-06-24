package models.oauth2;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
@Table(name = "oauth2_client_grant_type")
public class ClientGrantType extends Model{

	public static Finder<Long, ClientGrantType> find = new Finder<>(Long.class, ClientGrantType.class);

	@Column
	public Long grant_type_id;

	@Column(length = 20)
	public String client_type;;
	
	public ClientGrantType() {};

}
/**
  id integer NOT NULL,
  grant_type varchar(20) NOT NUL
  */