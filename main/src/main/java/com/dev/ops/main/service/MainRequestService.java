package com.dev.ops.main.service;

import com.dev.ops.commons.dao.MainRequestRepository;
import com.dev.ops.commons.domain.MainRequest;
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
public class MainRequestService implements RequestService<MainRequest.Dto> {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainRequestService.class);

    private final MainRequestRepository mainRequestRepository;

    public MainRequestService(MainRequestRepository mainRequestRepository) {
        this.mainRequestRepository = mainRequestRepository;
    }

    @Override
    public MainRequest.Dto create(HttpServletRequest request) {
        String clientAddr = NetworkUtil.getClientAddr(request);
        String body = NetworkUtil.extractRequestBody(request);
        LOGGER.info("Create request by -> {}, body -> {}", clientAddr, body);
        return new MainRequest.Dto(mainRequestRepository.save(new MainRequest(clientAddr, body)));
    }

    @Override
    public Page<MainRequest.Dto> find(HttpServletRequest request, Pageable pageable) {
        String clientAddr = NetworkUtil.getClientAddr(request);
        LOGGER.info("Find request by -> {}, pageable -> {}", clientAddr, pageable);
        return mainRequestRepository.findAllByIpAddress(clientAddr, pageable).map(MainRequest.Dto::new);
    }
}
