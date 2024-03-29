package br.com.dasa.teste.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.dasa.teste.model.Usuario;
import br.com.dasa.teste.repository.UsuarioRepository;

public class AutenticacaoToken extends OncePerRequestFilter{

	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;
	
	public AutenticacaoToken(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valido = tokenService.validaToken(token);
		if(valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}	

	
	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
	
	
	private void autenticarCliente(String token) {
		Long id = tokenService.getIdUsuario(token);
		Usuario usuario= usuarioRepository.findById(id).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
