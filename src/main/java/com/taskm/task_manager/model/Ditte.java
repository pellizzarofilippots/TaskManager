package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "DITTE", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_DITTE_PIVA", columnNames = {"P_IVA"})
})
public class Ditte {
    @Id
    @Column(name = "ID_DITTA", nullable = false)
    private Long id;

    @Column(name = "RAGIONE_SOCIALE", nullable = false, length = 320)
    private String ragioneSociale;

    @Column(name = "P_IVA", length = 11)
    private String pIva;

    @Column(name = "COD_FISCALE", length = 16)
    private String codFiscale;

    @Column(name = "TIPO_DITTA_ID")
    private Long tipoDittaId;

    @Column(name = "DATA_COSTITUZIONE")
    private LocalDate dataCostituzione;

    @Column(name = "LOGO")
    private byte[] logo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "LOGO_MIME_ID")
    private TipiMime logoMime;

    @Column(name = "DESCRIZIONE", length = 4000)
    private String descrizione;

    @ColumnDefault("0")
    @Column(name = "IND_CANC")
    private Boolean indCanc;

    @ColumnDefault("SYSDATE")
    @Column(name = "MOD_DATE", nullable = false)
    private LocalDate modDate;

}