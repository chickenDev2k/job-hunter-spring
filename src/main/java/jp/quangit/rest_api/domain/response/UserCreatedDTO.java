package jp.quangit.rest_api.domain.response;

import java.time.Instant;

import jp.quangit.rest_api.utils.constant.GenderEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatedDTO {
    private long id;
    private String name;
    private String email;
    private GenderEnum gender;
    private String address;
    private int age;
    private Instant createdAt;

}
