package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Représente une règle métier utilisée dans le système.
 * <p>
 * Une règle comprend un nom, une description, une structure JSON, un modèle,
 * ainsi que des requêtes SQL associées.
 * </p>
 */
@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Le nom est requis")
    private String name;

    private String description;
    private String json;
    private String template;
    private String sqlStr;
    private String sqlPart;

    /**
     * Constructeur par défaut.
     */
    public RuleName() {}

    /**
     * Constructeur avec tous les champs principaux.
     *
     * @param name        le nom de la règle
     * @param description la description de la règle
     * @param json        la structure JSON associée
     * @param template    le modèle de règle
     * @param sqlStr      la requête SQL principale
     * @param sqlPart     la sous-partie de la requête SQL
     */
    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    /**
     * @return l'identifiant de la règle
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id l'identifiant à définir
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return le nom de la règle
     */
    public String getName() {
        return name;
    }

    /**
     * @param name le nom à définir
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return la description de la règle
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description la description à définir
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return la structure JSON de la règle
     */
    public String getJson() {
        return json;
    }

    /**
     * @param json la structure JSON à définir
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * @return le modèle (template) associé à la règle
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template le modèle à définir
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * @return la requête SQL principale
     */
    public String getSqlStr() {
        return sqlStr;
    }

    /**
     * @param sqlStr la requête SQL à définir
     */
    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    /**
     * @return la sous-requête SQL ou partie de requête
     */
    public String getSqlPart() {
        return sqlPart;
    }

    /**
     * @param sqlPart la sous-requête SQL à définir
     */
    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
