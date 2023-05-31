package com.residencia.ecommerce.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.residencia.ecommerce.security.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) //habilita o cors
            .csrf(csrf -> csrf.disable()) //desabilita o csrf
            .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler)) //configura a classe para tratamento da excecao de autenticacao
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //define a politica de sessao
            .headers((headers) ->
            	headers
                    .frameOptions((frameOptions) -> frameOptions.sameOrigin()))
                	.authorizeHttpRequests(auth -> auth
                			.requestMatchers("/auth/**","/actuator/**", "/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**","/roles").permitAll() //define as rotas publicas/abertas
                            .requestMatchers(HttpMethod.GET,"/clientes/dto/**").hasAnyRole("CLIENTE", "ADMIN")
                            .requestMatchers(HttpMethod.GET,"/clientes/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/clientes/**").hasAnyRole("CLIENTE", "ADMIN")
                            .requestMatchers(HttpMethod.POST,"/clientes/**").hasAnyRole("ADMIN","CLIENTE", "USER")
                            .requestMatchers(HttpMethod.DELETE,"/clientes/**").hasRole("ADMIN")
                            .requestMatchers("/categorias/**").hasRole("ADMIN")

                            .requestMatchers(HttpMethod.GET, "/itempedidos/dto/**").hasAnyRole("CLIENTE", "ADMIN")
                            .requestMatchers(HttpMethod.POST, "/itempedidos/dto/**").hasAnyRole("CLIENTE", "ADMIN")
                            .requestMatchers(HttpMethod.GET, "/itempedidos/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/itempedidos/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/itempedidos/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/itempedidos/**").hasRole("ADMIN")
                            /*
                            .requestMatchers("/itempedidos/dto**").hasAnyRole("CLIENTE", "ADMIN")
                            .requestMatchers("/pedidos/**").hasRole("ADMIN")
                            .requestMatchers("/pedidos/dto**").hasAnyRole("CLIENTE", "ADMIN")
                            .requestMatchers("/test/user/**").hasAnyRole("USER", "ADMIN") *///autoriza o acesso a rotas por perfis
                            .anyRequest().authenticated()) //demais rotas, nao configuradas acima, so poderao ser acessadas mediante autenticacao
		;		
        	
		
		http.authenticationProvider(authenticationProvider()); //define o provedor de autenticacao

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); //define o filtro a ser aplicado no ciclo de vida da requisicao
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
