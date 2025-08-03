package jp.quangit.rest_api.domain.dto;

import java.time.Instant;

import jp.quangit.rest_api.utils.constant.GenderEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatedDTO {
    private long id;
    private String name;
    private GenderEnum gender;
    private String address;
    private int age;
    private Instant updatedAt;
}
