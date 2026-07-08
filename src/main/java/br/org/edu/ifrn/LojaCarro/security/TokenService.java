package br.org.edu.ifrn.LojaCarro.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String SECRET = "minha-chave-secreta";

    public String gerarToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create()
                .withIssuer("LojaCarro")
                .withSubject(username)
                .withExpiresAt(gerarExpiracao())
                .sign(algorithm);
    }

    public String validarToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.require(algorithm)
                .withIssuer("LojaCarro")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant gerarExpiracao() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}