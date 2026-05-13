package com.hospital.auth.service;

import com.hospital.auth.dto.AuthResponse;
import com.hospital.auth.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);
}