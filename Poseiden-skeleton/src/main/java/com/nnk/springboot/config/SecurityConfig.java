package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuration de la sécurité de l'application.
 * <p>
 * Cette configuration utilise Spring Security avec une authentification basée sur les sessions,
 * une stratégie de filtrage personnalisée, un encodeur de mots de passe et la gestion des accès selon les rôles.
 * </p>
 * 
 * <ul>
 *     <li>Les pages publiques incluent : "/", "/login", "/register", etc.</li>
 *     <li>Les utilisateurs authentifiés ont accès aux ressources principales.</li>
 *     <li>Les utilisateurs ayant le rôle ADMIN peuvent modifier ou supprimer des utilisateurs.</li>
 * </ul>
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean permettant de hacher les mots de passe avec l’algorithme BCrypt.
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
     * @param authConfig configuration de l'authentification
     * @return une instance de {@link AuthenticationManager}
     * @throws Exception en cas d'échec lors de la récupération du gestionnaire
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configure la chaîne de filtres de sécurité.
     * 
     * <ul>
     *     <li>CSRF est désactivé pour simplifier les appels POST sans token.</li>
     *     <li>Des accès sont accordés ou restreints selon les rôles et l'authentification.</li>
     *     <li>Le formulaire de login personnalisé est activé avec redirections spécifiques selon les cas.</li>
     *     <li>Le logout est entièrement configuré (invalidation de session, suppression des cookies, redirection).</li>
     * </ul>
     *
     * @param http l'objet {@link HttpSecurity} à configurer
     * @return la {@link SecurityFilterChain} configurée
     * @throws Exception si une erreur survient pendant la configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/register", "/user/validate", "/user/add").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/user/list", "/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").authenticated()
                .requestMatchers("/user/update/**", "/user/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/bidList/list", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

}
