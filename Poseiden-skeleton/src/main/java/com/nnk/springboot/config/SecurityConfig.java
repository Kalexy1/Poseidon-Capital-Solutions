package com.nnk.springboot.config;

import com.nnk.springboot.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de la sécurité de l'application.
 * <p>
 * Définit les règles d'autorisation, les pages publiques/privées,
 * le fournisseur d'authentification, l'encodeur de mot de passe,
 * et la page de connexion personnalisée.
 * </p>
 */
@Configuration
public class SecurityConfig {

    /**
     * Fournit un encodeur de mot de passe basé sur l'algorithme BCrypt.
     * Utilisé pour encoder et vérifier les mots de passe utilisateurs.
     *
     * @return une instance de {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Fournit un {@link DaoAuthenticationProvider} configuré avec le service utilisateur personnalisé
     * et l'encodeur de mot de passe.
     * <p>
     * Ce bean est injecté dans la chaîne de sécurité pour que Spring Security
     * utilise correctement ton {@link UserDetailsServiceImpl}.
     * </p>
     *
     * @param userDetailsService service de récupération des utilisateurs
     * @param passwordEncoder    encodeur de mots de passe
     * @return une instance de {@link DaoAuthenticationProvider}
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService,
                                                             BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Fournit le gestionnaire d'authentification principal de Spring Security.
     *
     * @param authConfig configuration d'authentification
     * @return une instance de {@link AuthenticationManager}
     * @throws Exception si une erreur survient lors de la construction
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configure les règles de sécurité de l'application :
     * - accès public/privé selon les URLs
     * - pages de connexion/déconnexion personnalisées
     * - gestion des rôles pour les accès sensibles
     *
     * @param http           l'objet de configuration HTTP
     * @param authProvider   le fournisseur d'authentification personnalisé
     * @return la chaîne de filtres de sécurité
     * @throws Exception en cas d'erreur de configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   DaoAuthenticationProvider authProvider) throws Exception {

        // Lier notre provider à Spring Security
        http.authenticationProvider(authProvider);

        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/login", "/register", "/user/validate", "/user/add").permitAll()
                    .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                    .requestMatchers("/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").authenticated()
                    .requestMatchers("/user/list","/user/update/**", "/user/delete/**").hasRole("ADMIN")
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
                .accessDeniedPage("/403")
            );

        return http.build();
    }
}
