package com.taskm.task_manager.service;

import com.taskm.task_manager.model.Utenti;
import com.taskm.task_manager.repository.UtentiRepository;
import com.taskm.task_manager.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtentiRepository utentiRepository;

    public CustomUserDetailsService(UtentiRepository utentiRepository) {
        this.utentiRepository = utentiRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("🔍 Tentativo login per username: " + username);
//        Utenti utente = utentiRepository.findAll().stream()
//                .filter(f->f.getUserid().equalsIgnoreCase(username))
//                .findFirst()
//                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + username));
//
//        System.out.println("✅ Utente trovato: " + utente.getUserid());
//        System.out.println("🔐 Password DB: " + utente.getPassword());
//        return new CustomUserDetails(utente);
//    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("🔍 Tentativo login per username: " + username);

        Utenti utente = utentiRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + username));

        System.out.println("✅ Utente trovato nel DB: " + utente.getUserid());
        System.out.println("🔐 Password DB: " + utente.getPassword());

        return new CustomUserDetails(utente);
    }

}
