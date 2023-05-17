package com.dev.ops.commons.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.Map;

public class NetworkUtil {

    private final static ObjectMapper MAPPER = new ObjectMapper();
    private final static TypeReference<Map<String, String>> MAP_TYPE_REFERENCE = new TypeReference<>() {};

    public static String getClientAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        return (ipAddress == null || ipAddress.isEmpty())
                ? request.getRemoteAddr()
                : extractClientAddress(ipAddress);
    }

    private static String extractClientAddress(String remoteAddr) {
        final int index = remoteAddr.indexOf(',');
        return index > 0 ? remoteAddr.substring(0, index) : remoteAddr;
    }

    public static String extractRequestBody(HttpServletRequest request) {
        try {
            return MAPPER.readValue(StreamUtils.copyToByteArray(request.getInputStream()), MAP_TYPE_REFERENCE).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
