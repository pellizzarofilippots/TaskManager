package com.taskm.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssegnazioneDto {
    private Long progettoId;
    private Long personaId;
    private Long ruoloId;
    private Boolean hasPrgGestisci;
    private Boolean hasAttAggiungi;
    private Boolean hasAttAssegna;
    private Boolean hasAttStato;
    private Boolean hasAttPrendi;
}
