package com.desafiopitang.usercar.api.v1.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiopitang.usercar.domain.dto.AuthenticationDTO;
import com.desafiopitang.usercar.domain.model.User;
import com.desafiopitang.usercar.domain.service.impl.UserServiceImpl;
import com.desafiopitang.usercar.infra.security.TokenService;

@RestController
@RequestMapping("api")
public class AuthenticationController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/signin")
    public ResponseEntity<Object> login(@RequestBody  AuthenticationDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }
	
	@GetMapping("/me")
	public ResponseEntity<Object> userLongin(HttpServletRequest requisicao){
		var usuario = userServiceImpl.loadUserByUsername(requisicao.getRemoteUser());
		return ResponseEntity.ok(usuario);
	}

}
