package com.dev.ops.commons.dao;

import com.dev.ops.commons.domain.AdminRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dmitriy Lyashenko
 */
public interface AdminRequestRepository extends JpaRepository<AdminRequest, Integer> {
    Page<AdminRequest> findAllByIpAddressOrderByCreatedDateDesc(String ipAddress, Pageable pageable);
}
