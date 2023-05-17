package com.dev.ops.admin.controller;

import com.dev.ops.commons.domain.AdminRequest;
import com.dev.ops.commons.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dmitriy Lyashenko
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class AdminController {

    private RequestService<AdminRequest.Dto> requestService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(HttpServletRequest request) {
        return ResponseEntity.ok(requestService.create(request));
    }

    @GetMapping(value = "/find")
    public ResponseEntity<?> find(HttpServletRequest request, Pageable pageable) {
        return ResponseEntity.ok(requestService.find(request, pageable));
    }

    @Autowired
    public void setRequestService(RequestService<AdminRequest.Dto> requestService) {
        this.requestService = requestService;
    }
}
