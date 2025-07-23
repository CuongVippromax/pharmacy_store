package com.pharmacy_store.domain.dto.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultPagination {
    private Meta meta;
    private Object result;
}
