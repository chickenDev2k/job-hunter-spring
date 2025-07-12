package jp.quangit.rest_api.service;

import jp.quangit.rest_api.domain.User;
import jp.quangit.rest_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUser() {
        return this.userRepository.findAll();
    }
    public User fetchUserById(long id){
        return this.userRepository.findById(id);
    }

    public User handleSaveUser(User user){
       return  this.userRepository.save(user);

    }
    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }


}
