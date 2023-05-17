package com.dev.ops.admin.service;

import com.dev.ops.commons.dao.AdminRequestRepository;
import com.dev.ops.commons.domain.AdminRequest;
import com.dev.ops.commons.service.RequestService;
import com.dev.ops.commons.util.NetworkUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Dmitriy Lyashenko
 */
@Service
public class AdminRequestService implements RequestService<AdminRequest.Dto> {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminRequestService.class);

    private final AdminRequestRepository adminRequestRepository;

    public AdminRequestService(AdminRequestRepository adminRequestRepository) {
        this.adminRequestRepository = adminRequestRepository;
    }

    @Override
    public AdminRequest.Dto create(HttpServletRequest request) {
        String clientAddr = NetworkUtil.getClientAddr(request);
        String body = NetworkUtil.extractRequestBody(request);
        LOGGER.info("Create request by -> {}, body -> {}", clientAddr, body);
        return new AdminRequest.Dto(adminRequestRepository.save(new AdminRequest(clientAddr, body)));
    }

    @Override
    public Page<AdminRequest.Dto> find(HttpServletRequest request, Pageable pageable) {
        String clientAddr = NetworkUtil.getClientAddr(request);
        LOGGER.info("Find request by -> {}, pageable -> {}", clientAddr, pageable);
        return adminRequestRepository.findAllByIpAddress(clientAddr, pageable).map(AdminRequest.Dto::new);
    }
}
