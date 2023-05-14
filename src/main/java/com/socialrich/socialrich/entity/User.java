package com.socialrich.socialrich.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @ManyToOne
    @JoinColumn(name = "red_social_favorita_id")
    private RedesSociales redSocialFavorita;

    @ManyToMany
    @JoinTable(
            name = "user_redes_sociales",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "red_social_id")
    )
    private List<RedesSociales> redesSociales;

    public User() {
    }

    public User(String name, String surname, RedesSociales redSocialFavorita, List<RedesSociales> redesSociales) {
        this.name = name;
        this.surname = surname;
        this.redSocialFavorita = redSocialFavorita;
        this.redesSociales = redesSociales;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public RedesSociales getRedSocialFavorita() {
        return redSocialFavorita;
    }

    public void setRedSocialFavorita(RedesSociales redSocialFavorita) {
        this.redSocialFavorita = redSocialFavorita;
    }

    public List<RedesSociales> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(List<RedesSociales> redesSociales) {
        this.redesSociales = redesSociales;
    }
}
