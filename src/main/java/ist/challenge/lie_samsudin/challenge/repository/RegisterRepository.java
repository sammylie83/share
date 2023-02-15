package ist.challenge.lie_samsudin.challenge.repository;


import ist.challenge.lie_samsudin.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<User, Long>{
	 Optional<User> findByUsername(String username);
}
