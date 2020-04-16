package br.com.rest.api.spring.mscorinthans.services.security;

import br.com.rest.api.spring.mscorinthans.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${corinthans.jwt.expiration}")
    private String expiration;

    @Value("${corinthans.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {

        User logado = (User) authentication.getPrincipal();
        Date dataAtual = new Date();
        Date dataExpiracao = new Date(dataAtual.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do Brasileirão")
                .setSubject(logado.getId().toString())
                .setIssuedAt(dataAtual)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validarToken(String token) {

        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Long getId(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
