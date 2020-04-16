package br.com.rest.api.spring.mscorinthans.security;

import br.com.rest.api.spring.mscorinthans.dto.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {

        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);

        ObjectMapper mapper = new ObjectMapper();
        res.getWriter().write(mapper.writeValueAsString(new Error(403,"Forbidden","Acesso Negado!")
        ));
    }
}
