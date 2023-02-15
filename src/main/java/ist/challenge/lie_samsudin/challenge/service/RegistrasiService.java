package ist.challenge.lie_samsudin.challenge.service;

import ist.challenge.lie_samsudin.challenge.entity.User;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface RegistrasiService {
	
	public List<User> findAll();
	public User findById(Long id);
	public User findByUserName(String userName);
	public User save(User user);
	public boolean update(User user);
	public boolean deleteById(Long id);


}
