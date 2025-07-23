package com.pharmacy_store.domain.dto.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {
    // trang hiện tại đang đứng
    private int page;
    // số lượng phần tử trong 1 trang
    private int pageSize;
    // số lượng trang
    private int pages;
    // tổng số lượng phần tử
    private long total;
}
