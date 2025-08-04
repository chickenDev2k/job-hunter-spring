package jp.quangit.rest_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jp.quangit.rest_api.domain.User;
import jp.quangit.rest_api.domain.dto.LoginDTO;
import jp.quangit.rest_api.domain.dto.ResLoginDTO;
import jp.quangit.rest_api.service.UserService;
import jp.quangit.rest_api.utils.SecurityUtil;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;
    private final UserService userService;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil,
            UserService userService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        // nap input vao security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUserName(), loginDTO.getPassword());

        // xac thuc nguoi dung => can viet ham loadUserByUserName

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // create token
        String accessToken = this.securityUtil.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResLoginDTO res = new ResLoginDTO();
        User currentUserDB = userService.handleGetUserByUsername(loginDTO.getUserName());
        if (currentUserDB != null) {
            ResLoginDTO.UserLogin userLogin = new ResLoginDTO.UserLogin(currentUserDB.getId(), currentUserDB.getEmail(),
                    currentUserDB.getName());
            res.setUserLogin(userLogin);
        }

        res.setAccessToken(accessToken);
        return ResponseEntity.ok().body(res);
    }

}
