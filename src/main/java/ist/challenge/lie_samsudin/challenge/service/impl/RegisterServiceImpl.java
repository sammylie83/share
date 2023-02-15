package ist.challenge.lie_samsudin.challenge.service.impl;



import ist.challenge.lie_samsudin.challenge.entity.User;
import ist.challenge.lie_samsudin.challenge.service.RegistrasiService;
import ist.challenge.lie_samsudin.challenge.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegisterServiceImpl implements RegistrasiService {

	private RegisterRepository repository;
	private static final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);
	
	public RegisterServiceImpl() {

	}

	@Autowired
	public RegisterServiceImpl(RegisterRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();
		repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public User findById(Long id) {
		User c = repository.findById(id).orElse(null);
		return c;
	}

	@Override
	public User findByUserName(String username) {
		return repository.findByUsername(username).orElse(null);
	}

	@Override
	public User save(User user) {
		User duplicateUser = this.findByUserName(user.getUsername());
		if (duplicateUser == null) { // No user Found with 
			return repository.save(user);
		}
		log.info("User with username ={} found in database", user.getUsername());
		return null;
	}

	@Override
	public boolean update(User user) {
		log.info("User to be updated: id={}", user.getId());
		User userFromDb = this.findById(user.getId());
		
		if (userFromDb !=null) {
			// User found, update
			log.info("User found in Database ********** updating the user with id={}", user.getId());
			repository.save(user);
			return true;

		} else {
			//User not found, cannot update database
			return false;
		}

	}

	@Override
	public boolean deleteById(Long id) {
		User c = this.findById(id);
		if(c != null) {
			repository.delete(c);
			return true;
		}else {
			return false;
		}
	}


}
