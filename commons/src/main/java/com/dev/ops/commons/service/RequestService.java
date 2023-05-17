package com.dev.ops.commons.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Dmitriy Lyashenko
 */
public interface RequestService<T> {
    T create(HttpServletRequest request);
    Page<T> find(HttpServletRequest request, Pageable pageable);
}
