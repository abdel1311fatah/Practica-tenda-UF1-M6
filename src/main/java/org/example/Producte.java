package org.example;

import java.io.Serializable;

public class Producte implements Serializable {

    Double preu;
    String nom;
    int id;

    Producte(){
    }
    Producte(Double preu, String nom, int id){
        this.id = id;
        this.nom = nom;
        this.preu = preu;
    }

    public Double getPreu() {
        return preu;
    }

    public void setPreu(Double preu) {
        this.preu = preu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Producte{" +
                "preu=" + preu +
                ", nom='" + nom + '\'' +
                ", id=" + id +
                '}';
    }
}
