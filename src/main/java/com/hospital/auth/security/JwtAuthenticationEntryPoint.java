package com.hospital.auth.security;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.AuthenticationEntryPoint;

import org.springframework.stereotype.Component;

import com.hospital.common.response.ApiResponse;

@Component
public class JwtAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    @Override
    public void commence(

            HttpServletRequest request,

            HttpServletResponse response,

            AuthenticationException authException)

            throws IOException,
            ServletException {

        response.setStatus(
                HttpServletResponse.SC_UNAUTHORIZED
        );

        response.setContentType(
                "application/json"
        );

        ApiResponse<Object> apiResponse =

                ApiResponse.builder()

                        .success(false)

                        .message("Unauthorized")

                        .build();

        new ObjectMapper()

                .writeValue(
                        response.getOutputStream(),
                        apiResponse
                );
    }
}