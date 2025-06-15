package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder; // Per codificare le password

    @GetMapping("/login")
    public String showLoginForm() {
        return "loginForm"; // Ritorna il nome del template HTML
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "registerForm"; // Ritorna il nome del template HTML
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               @ModelAttribute("credentials") Credentials credentials,
                               Model model) {
        if (credentialsService.getCredentials(credentials.getUsername()) != null) {
            model.addAttribute("errorMessage", "Username already exists!");
            return "registerForm";
        }

        // Codifica la password prima di salvarla
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        // Imposta il ruolo di default (ad esempio, "REGISTERED")
        credentials.setRole("REGISTERED"); // Puoi anche gestire diversi tipi di registrazione

        // Salva le credenziali e l'utente
        credentialsService.saveCredentials(credentials);
        user.setCredentials(credentials); // Collega le credenziali all'utente
        userService.saveUser(user);

        return "registrationSuccess"; // Pagina di successo registrazione
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success"; // Pagina di successo dopo il login
    }

    @GetMapping("/")
    public String showIndexPage() {
        return "index"; // Pagina home/index
    }
}