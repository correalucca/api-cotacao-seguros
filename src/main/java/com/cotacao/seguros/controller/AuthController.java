package com.cotacao.seguros.controller;

import com.cotacao.seguros.dto.LoginRequestDTO;
import com.cotacao.seguros.dto.LoginResponseDTO;
import com.cotacao.seguros.dto.SignupRequestDTO;
import com.cotacao.seguros.entity.Usuario;
import com.cotacao.seguros.enums.ResponseEnum;
import com.cotacao.seguros.exception.BusinessException;
import com.cotacao.seguros.repository.UsuarioRepository;
import com.cotacao.seguros.security.JwtTokenProvider;
import com.cotacao.seguros.ApiResponse.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtTokenProvider tokenProvider;
    private final UsuarioRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authManager,
                          JwtTokenProvider tokenProvider,
                          UsuarioRepository userRepo,
                          PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.tokenProvider = tokenProvider;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ApiResponse<Void> signup(@Validated @RequestBody SignupRequestDTO dto) {
        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new BusinessException(ResponseEnum.DUPLICATE_EMAIL);
        }
        Usuario user = Usuario.builder()
                .username(dto.getEmail())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        userRepo.save(user);
        return ApiResponse.success();
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO> login(@Validated @RequestBody LoginRequestDTO dto) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        String token = tokenProvider.generateToken(auth);
        return ApiResponse.success(new LoginResponseDTO(token));
    }
}
