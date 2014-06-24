package models.oauth2;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
@Table(name = "oauth2_grant_type")
public class GrantType extends Model{

	public static Finder<Long, GrantType> find = new Finder<>(Long.class, GrantType.class);

	@Id
	@Column
	@GeneratedValue
	public Long id;

	@Column(length = 20)
	public String grant_type = "";

	public Long user_id;
	
	public GrantType() {};

}
/**
  id integer NOT NULL,
  grant_type varchar(20) NOT NUL
  */