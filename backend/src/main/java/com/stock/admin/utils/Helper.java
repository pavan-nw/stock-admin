package com.stock.admin.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * The type Helper.
 */
public class Helper {

    /**
     * Page request for page request.
     *
     * @param pageNum       the page num
     * @param size          the size
     * @param sortType      the sort type
     * @param proertyToSort the proerty to sort
     * @return the page request
     */
    public static PageRequest pageRequestFor(int pageNum, int size, String sortType, String... proertyToSort) {
        return PageRequest.of(pageNum - 1, size, Sort.by(Sort.Direction.fromString(sortType), proertyToSort));
    }
}
