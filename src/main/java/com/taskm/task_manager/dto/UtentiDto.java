package com.taskm.task_manager.dto;

import com.taskm.task_manager.model.Anagrafica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtentiDto {
    // L'ID è necessario per l'aggiornamento (PUT)
    private Long id; // ID_UTENTE
    private String userId;
    // La Password non viene passata qui (solo nell'operazione di login/creazione)
    private String password;
    private LocalDate dataScadenzaPwd;
    private String codiceAttivazione;
    private Long tentativiFalliti;
    private Boolean forzaCambioPwd;

    // Relazioni mappate come ID semplici per il DTO
    private Long statoUtenteId; // STATO_UTENTE_ID
    private Long ruoloId;

    // **Aggiunto**
    private Long anagraficaId;
}