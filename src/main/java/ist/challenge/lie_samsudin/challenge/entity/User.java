package ist.challenge.lie_samsudin.challenge.entity;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Column
    private String password;


	@Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public User(long id, String username, String password) {
    	this.id = id;
        this.username = username;
        this.password = password;
    }
    

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
    public User() {

    }
    
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}





	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	

}
