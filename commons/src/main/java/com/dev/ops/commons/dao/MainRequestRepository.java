package com.dev.ops.commons.dao;

import com.dev.ops.commons.domain.MainRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dmitriy Lyashenko
 */
public interface MainRequestRepository extends JpaRepository<MainRequest, Integer> {
    Page<MainRequest> findAllByIpAddressOrderByCreatedDateDesc(String ipAddress, Pageable pageable);
}
