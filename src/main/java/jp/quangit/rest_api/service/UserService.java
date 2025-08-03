package jp.quangit.rest_api.service;

import jp.quangit.rest_api.domain.dto.Meta;
import jp.quangit.rest_api.domain.dto.ResultPaginationDTO;
import jp.quangit.rest_api.domain.dto.User;
import jp.quangit.rest_api.domain.dto.UserDTO;
import jp.quangit.rest_api.repository.UserRepository;
import jp.quangit.rest_api.utils.ConvertWithDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResultPaginationDTO fetchAllUser(Specification<User> specification, Pageable pageable) {
        Page<User> pageUser = this.userRepository.findAll(specification, pageable);
        List<UserDTO> pageUserRes = new ArrayList<>();
        ResultPaginationDTO rs = new ResultPaginationDTO();
        Meta mt = new Meta();
        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());
        mt.setPages(pageUser.getTotalPages());
        mt.setTotal(pageUser.getTotalElements());

        rs.setMeta(mt);
        // edit response without password
        for (User user : pageUser) {
            pageUserRes.add(ConvertWithDTO.convertToUserDTO(user));
        }
        rs.setResult(pageUserRes);

        return rs;
    }

    public User fetchUserById(long id) {
        return this.userRepository.findById(id);
    }

    public User handleGetUserByUsername(String userName) {
        return this.userRepository.findByEmail(userName);
    }

    public User handleSaveUser(User user) {
        if (this.userRepository.existsByEmail(user.getEmail())) {
            return null;
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        Instant createdAt = Instant.now();
        user.setCreatedAt(createdAt);
        return this.userRepository.save(user);

    }

    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

}
