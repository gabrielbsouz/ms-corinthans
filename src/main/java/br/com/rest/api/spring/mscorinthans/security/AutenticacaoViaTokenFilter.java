package br.com.rest.api.spring.mscorinthans.security;

import br.com.rest.api.spring.mscorinthans.models.User;
import br.com.rest.api.spring.mscorinthans.repositories.UserRepository;
import br.com.rest.api.spring.mscorinthans.services.security.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);
        boolean tokenValido = tokenService.validarToken(token);
        if(tokenValido){
            autenticarLogin(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private void autenticarLogin(String token) {
        Long id = tokenService.getId(token);
        User user = userRepository.findById(id).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest){

        String token = httpServletRequest.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")){
            return null;
        } else{
            return token.substring(7, token.length());
        }

    }
}
