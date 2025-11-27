package com.taskm.task_manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProgettoXAnagraficaId implements Serializable {
    private static final long serialVersionUID = -4307307218766173406L;
    @Column(name = "PROGETTO_ID", nullable = false)
    private Long progettoId;

    @Column(name = "PERSONA_ID", nullable = false)
    private Long personaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProgettoXAnagraficaId entity = (ProgettoXAnagraficaId) o;
        return Objects.equals(this.personaId, entity.personaId) &&
                Objects.equals(this.progettoId, entity.progettoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personaId, progettoId);
    }

}