package com.portex.miexperiencia.infraestructura.configuracion;

import com.portex.compartido.infraestructura.seguridad.jwt.JwtCustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ConfiguracionSeguridad{

    private final JwtCustomFilter jwtCustomFilter;

    public ConfiguracionSeguridad(JwtCustomFilter jwtCustomFilter) {
        this.jwtCustomFilter = jwtCustomFilter;
    }

    @Bean
    @Order(2) 
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sess ->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(customizeRequest ->{
                    customizeRequest
                            .requestMatchers("/login").permitAll()
                            .requestMatchers(HttpMethod.GET,"/trabajadores").permitAll()
                            .requestMatchers(HttpMethod.GET,"/trabajadores/perfil").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET,"/servicios").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET,"/servicios/**").hasAnyRole("ADMIN","CLIENTE")
                            .requestMatchers(HttpMethod.POST,"/pedidos").hasAnyRole("ADMIN","CLIENTE")
                            .anyRequest()
                            .authenticated();
                })
                .addFilterBefore(jwtCustomFilter, UsernamePasswordAuthenticationFilter.class);


        return  httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
