package br.org.edu.ifrn.LojaCarro.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()   // rotas públicas
                        .requestMatchers("/carro/salvar").hasRole("ADMIN")  // só admin pode salvar
                        .requestMatchers("/carro/{id}").hasRole("ADMIN")    // atualizar e deletar só admin
                        .requestMatchers("/carro").hasAnyRole("USER","ADMIN") // GET /carro todos podem ver
                        .anyRequest().authenticated()
                )
                .httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .roles("ADMIN")
                .build();

        UserDetails vendedor = User.withDefaultPasswordEncoder()
                .username("vendedor")
                .password("1234")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, vendedor);
    }
}