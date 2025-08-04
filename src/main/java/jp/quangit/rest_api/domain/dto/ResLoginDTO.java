package jp.quangit.rest_api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class ResLoginDTO {
    private String accessToken;
    private UserLogin UserLogin;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserLogin {
        private long id;
        private String email;
        private String name;
    }
}
