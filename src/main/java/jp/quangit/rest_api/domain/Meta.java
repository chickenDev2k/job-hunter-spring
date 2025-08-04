package jp.quangit.rest_api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {
    private int page;
    private int pageSize;
    private int pages;
    private long total;
}
// current: page, //trang hiện tại
// pageSize: limit, //số lượng bản ghi đã lấy
// pages: totalPages, //tổng số trang với điều kiện query
// total: totalItems // tổng số phần tử (số bản ghi)
