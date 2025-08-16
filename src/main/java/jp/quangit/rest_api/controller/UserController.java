package jp.quangit.rest_api.controller;

import jp.quangit.rest_api.domain.RestResponse;
import jp.quangit.rest_api.domain.User;
import jp.quangit.rest_api.domain.response.ResultPaginationDTO;
import jp.quangit.rest_api.domain.response.UserCreatedDTO;
import jp.quangit.rest_api.domain.response.UserDTO;
import jp.quangit.rest_api.domain.response.UserUpdatedDTO;
import jp.quangit.rest_api.repository.UserRepository;
import jp.quangit.rest_api.service.UserService;
import jp.quangit.rest_api.utils.ConvertWithDTO;
import jp.quangit.rest_api.utils.annotation.ApiMessage;
import jp.quangit.rest_api.utils.error.IdInvalidException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // get all users
    // with filter and pagination
    @GetMapping("/users")
    @ApiMessage("fetch all user")
    public ResponseEntity<ResultPaginationDTO> fetchAllUser(
            @Filter Specification<User> spec,
            Pageable pageable)

    {

        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchAllUser(spec, pageable));
    }

    // get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<?> fetchUserById(@PathVariable("id") long id) {
        RestResponse rs = new RestResponse<>();
        UserDTO resUser = new UserDTO();
        User user = this.userService.fetchUserById(id);
        if (user != null) {
            resUser = ConvertWithDTO.convertToUserDTO(user);
        } else {
            rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
            rs.setError("Bad request");
            rs.setMessage("ユーザーが見つかりませんでした");
        }
        return user != null ? ResponseEntity.status(HttpStatus.OK).body(resUser) : ResponseEntity.badRequest().body(rs);
    }

    // create new user
    @PostMapping("/users/create")

    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user) throws IdInvalidException {
        RestResponse rs = new RestResponse<User>();
        UserCreatedDTO userResDTO = new UserCreatedDTO();
        User newUser = this.userService.handleSaveUser(user);
        if (newUser != null) {
            userResDTO = ConvertWithDTO.convertToUserCreatedDTO(newUser);
            rs.setData(userResDTO);
        } else {
            rs.setError("bad request");
            rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
            rs.setMessage("tai khoan da ton tai !!!");
        }

        return newUser != null ? ResponseEntity.status(HttpStatus.CREATED).body(userResDTO)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rs);
    }

    // update user
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws IdInvalidException {
        RestResponse rs = new RestResponse<>();
        UserUpdatedDTO userUpdatedDTO = new UserUpdatedDTO();
        User existUser = this.userService.fetchUserById(user.getId());
        if (existUser != null) {
            existUser.setName(user.getName());
            existUser.setAddress(user.getAddress());
            existUser.setGender(user.getGender());
            existUser.setAge(user.getAge());
            existUser.setUpdatedAt(Instant.now());
            userUpdatedDTO = ConvertWithDTO.convertToUserUpdatedDTO(existUser);
            this.userService.handleSaveUser(existUser);
        } else {
            rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
            rs.setError("Bad request");
            rs.setMessage("ユーザーが見つかりませんでした");
        }
        return existUser != null ? ResponseEntity.ok(userUpdatedDTO) : ResponseEntity.badRequest().body(rs);
    }

    // delete user by id
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id)
            throws IdInvalidException {
        RestResponse rs = new RestResponse<>();
        boolean checkExit = this.userRepository.existsById(id);
        if (checkExit) {
            this.userService.handleDeleteUser(id);
        } else {
            rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
            rs.setMessage("tai khoan nay khong ton tai");
        }

        return checkExit ? ResponseEntity.ok().body(null) : ResponseEntity.badRequest().body(rs);
    }
}
