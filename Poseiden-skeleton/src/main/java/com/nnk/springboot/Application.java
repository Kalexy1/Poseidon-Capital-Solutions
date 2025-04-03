package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée principal de l'application Spring Boot.
 * <p>
 * Cette classe lance l'application à l'aide de {@link SpringApplication}.
 * L'annotation {@code @SpringBootApplication} active la configuration automatique,
 * le scan des composants, et d'autres fonctionnalités de Spring Boot.
 * </p>
 */
@SpringBootApplication
public class Application {

    /**
     * Méthode principale qui démarre l'application Spring Boot.
     *
     * @param args les arguments de ligne de commande passés lors du démarrage
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
