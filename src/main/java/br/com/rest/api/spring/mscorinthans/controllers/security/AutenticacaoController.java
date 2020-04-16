package br.com.rest.api.spring.mscorinthans.controllers.security;

import br.com.rest.api.spring.mscorinthans.dto.Token;
import br.com.rest.api.spring.mscorinthans.forms.LoginFormPost;
import br.com.rest.api.spring.mscorinthans.services.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Token> autenticar(@RequestBody @Valid LoginFormPost loginFormPost) {
        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(loginFormPost.getEmail(), loginFormPost.getSenha());

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new Token(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
