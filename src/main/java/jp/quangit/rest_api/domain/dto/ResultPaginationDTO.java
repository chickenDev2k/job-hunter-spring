package jp.quangit.rest_api.domain.dto;

import jp.quangit.rest_api.domain.Meta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultPaginationDTO {

    private Meta meta;
    private Object result;
}
