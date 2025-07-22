package com.inicial.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inicial.model.Usuario;
import com.inicial.security.JwtUtil;
import com.inicial.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
            Usuario usuario = usuarioService.saveUsuario(
                request.get("username"),("senha"));
            return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<Usuario> usuarioOpt = usuarioService.findByUsername(request.get("username"));
        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(request.get("senha"))) {
            String token = JwtUtil.generateToken(usuarioOpt.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Usuário ou senha inválidos");
    }
    

}
