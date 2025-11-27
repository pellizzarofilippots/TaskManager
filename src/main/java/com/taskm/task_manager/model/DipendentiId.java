package com.taskm.task_manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class DipendentiId implements Serializable {
    private static final long serialVersionUID = 404165378773126618L;
    @Column(name = "ID_DIPENDENTE", nullable = false)
    private Long idDipendente;

    @Column(name = "DATA_ASSUNZIONE", nullable = false)
    private LocalDate dataAssunzione;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DipendentiId entity = (DipendentiId) o;
        return Objects.equals(this.idDipendente, entity.idDipendente) &&
                Objects.equals(this.dataAssunzione, entity.dataAssunzione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDipendente, dataAssunzione);
    }

}