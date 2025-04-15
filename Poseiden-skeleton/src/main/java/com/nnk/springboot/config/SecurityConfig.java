package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de la sécurité de l'application.
 * Utilise Spring Security avec une authentification basée sur les sessions, un encodeur de mots de passe et une gestion des accès par rôle.
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean pour l'encodeur de mots de passe utilisant l'algorithme BCrypt.
     *
     * @return un encodeur de mots de passe {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Fournit un {@link AuthenticationManager} basé sur la configuration d'authentification de Spring Security.
     *
     * @param authConfig configuration d'authentification
     * @return une instance de {@link AuthenticationManager}
     * @throws Exception en cas d'échec lors de la récupération du gestionnaire
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configure la chaîne de filtres de sécurité.
     * Définit les règles de sécurité pour les URL publiques, privées et les autorisations d'accès selon les rôles.
     * 
     * @param http l'objet {@link HttpSecurity} à configurer
     * @return la {@link SecurityFilterChain} configurée
     * @throws Exception si une erreur survient pendant la configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/login", "/register", "/user/validate", "/user/add").permitAll()
                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                .requestMatchers("/user/list", "/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").authenticated()
                                .requestMatchers("/user/update/**", "/user/delete/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/bidList/list", true)
                                .failureUrl("/login?error=true")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                )
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/403"));

        return http.build();
    }
}
