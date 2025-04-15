package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * Représente un utilisateur de l'application.
 * <p>
 * Cette entité contient les informations d'identification et de rôle de chaque utilisateur,
 * utilisées pour l'authentification et l'autorisation dans le système.
 * </p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    /**
     * Identifiant unique de l'utilisateur (généré automatiquement).
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nom d'utilisateur (doit être unique et non vide).
     */
    @NotBlank(message = "Username is mandatory")
    @Column(name = "username", length = 191, unique = true)
    private String username;

    /**
     * Mot de passe de l'utilisateur.
     * <p>
     * Le mot de passe doit :
     * <ul>
     *     <li>contenir au moins 8 caractères</li>
     *     <li>contenir au moins une lettre majuscule</li>
     *     <li>contenir au moins un chiffre</li>
     *     <li>contenir au moins un caractère spécial</li>
     * </ul>
     * </p>
     */
    @NotBlank(message = "Password is mandatory")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}:\";'<>?,./\\\\]).{8,}$",
        message = "Password must be at least 8 characters long and include at least one uppercase letter, one number, and one special character"
    )
    
    private String password;

    /**
     * Nom complet de l'utilisateur.
     */
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    /**
     * Rôle attribué à l'utilisateur (ex : ADMIN, USER).
     */
    @NotBlank(message = "Role is mandatory")
    private String role;

    /** @return l'identifiant de l'utilisateur */
    public Integer getId() { return id; }

    /** @param id définit l'identifiant de l'utilisateur */
    public void setId(Integer id) { this.id = id; }

    /** @return le nom d'utilisateur */
    public String getUsername() { return username; }

    /** @param username définit le nom d'utilisateur */
    public void setUsername(String username) { this.username = username; }

    /** @return le mot de passe */
    public String getPassword() { return password; }

    /** @param password définit le mot de passe */
    public void setPassword(String password) { this.password = password; }

    /** @return le nom complet */
    public String getFullname() { return fullname; }

    /** @param fullname définit le nom complet */
    public void setFullname(String fullname) { this.fullname = fullname; }

    /** @return le rôle de l'utilisateur */
    public String getRole() { return role; }

    /** @param role définit le rôle de l'utilisateur */
    public void setRole(String role) { this.role = role; }
}
