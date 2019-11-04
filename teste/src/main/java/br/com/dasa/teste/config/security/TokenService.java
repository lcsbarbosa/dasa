package br.com.dasa.teste.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.dasa.teste.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${teste.jwt.expiration}")
	private Long expiration;
	
	@Value("${teste.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authenticate) {
		Usuario logado = (Usuario) authenticate.getPrincipal();
		Date dataAtual = new Date();
		Date dataExpiracao = new Date(dataAtual.getTime() + expiration);
		
		return Jwts.builder()
				.setIssuer("API Dasa")
				.setSubject(logado.getId().toString())
				.setIssuedAt(dataAtual)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
}
