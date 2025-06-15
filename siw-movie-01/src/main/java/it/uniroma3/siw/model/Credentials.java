package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne; // Aggiungi questa importazione

@Entity
public class Credentials {

    @Id // Aggiungi questa annotazione
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Aggiungi questa annotazione
    private Long id; // Aggiungi questo campo

    private String username;
    private String password;
    private String role; // Es: "ADMIN", "REGISTERED", "OCCASIONAL"

    @OneToOne(mappedBy = "credentials") // Mappa la relazione inversa
    private User user; // Aggiungi questo campo per la relazione con User

    // Getter e Setter per 'id' e 'user'
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, password, role, username); // Aggiorna hashCode
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Credentials other = (Credentials) obj;
        return Objects.equals(id, other.id) && Objects.equals(password, other.password) // Aggiorna equals
                && Objects.equals(role, other.role) && Objects.equals(username, other.username);
    }
}