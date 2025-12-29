package com.taskm.task_manager.security;



import com.taskm.task_manager.model.Utenti;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Utenti utente;

    public CustomUserDetails(Utenti utente) {
        this.utente = utente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }



    @Override
    public String getUsername() {
        return utente.getUserid();
    }

    public Long getRuolo(){return utente.getRuolo().getId();}

    public Long getId() {
        return utente.getId();
    }

    public Long getAnagraficaId() {
        return utente.getAnagrafica().getId();  // ← Prende l'ID dall'anagrafica collegata
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;//utente.getTentativiFalliti() == null || utente.getTentativiFalliti() < 3;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;// utente.getStatoUtente() != null; // oppure una condizione più precisa
    }
}

