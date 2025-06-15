package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne; // Aggiungi questa importazione

@Entity
public class User {

    @Id // Aggiungi questa annotazione
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Aggiungi questa annotazione
    private Long id; // Aggiungi questo campo

    private String name;
    private String surname;
    private String email;
    private int telephoneNumber;

    @OneToOne // Aggiungi questa relazione per collegare User a Credentials
    private Credentials credentials; // Aggiungi questo campo

    // Getter e Setter per 'id' e 'credentials'
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Credentials getCredentials() {
        return credentials;
    }
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getTelephoneNumber() {
        return telephoneNumber;
    }
    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    @Override
    public int hashCode() {
        return Objects.hash(email, id, name, surname, telephoneNumber); // Aggiorna hashCode
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(email, other.email) && Objects.equals(id, other.id) // Aggiorna equals
                && Objects.equals(name, other.name) && Objects.equals(surname, other.surname)
                && telephoneNumber == other.telephoneNumber;
    }
}