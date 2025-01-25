package com.sweetsavvy.core.model;

import java.util.List;

public record PageData<T>(
        int currentPage,
        int totalPage,
        long totalElement,
        List<T> content
) {
}
