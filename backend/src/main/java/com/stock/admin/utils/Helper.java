package com.stock.admin.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class Helper {

    public static PageRequest pageRequestFor(int pageNum, int size, String sortType, String... proertyToSort) {
        return PageRequest.of(pageNum-1, size, Sort.by(Sort.Direction.fromString(sortType), proertyToSort));
    }
}
