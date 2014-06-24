package models.oauth2;

import javax.persistence.*;
import scala.Option;
import play.db.ebean.Model;

@Entity
@Table(name = "user")
public class User extends Model{

	public static Finder<Long, User> find = new Finder<>(Long.class, User.class);

	@Id
	@Column
	@GeneratedValue
	public Long id;

	@Column
	public String username;

	@Column
	public String email;

	@Column
	public String password;
	
	public User() {};

  public static User findUser(String userName, String Password) {
	  
	  // could do with MD5 hashing... password....
		return find.where().eq("username", userName).findUnique();
  }

  public static  User getById(Long id) {
		return  find.byId(id);
  }
}