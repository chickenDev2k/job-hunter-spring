package jp.quangit.rest_api.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLoginDTO {
    @NotBlank(message = "username must be not blank")
    @JsonProperty("username")
    private String userName;

    @NotBlank(message = "password must be not blank")
    private String password;

}
