package com.taskm.task_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "TIPI_MIME", schema = "WKSP_TASKMAN", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_TIPI_MIME_ETICHETTA", columnNames = {"ETICHETTA"}),
        @UniqueConstraint(name = "UNQ_TIPI_MIME_MIME", columnNames = {"MIME"})
})
public class TipiMime {
    @Id
    @Column(name = "ID_MIME", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CATEGORIA_ID", nullable = false)
    private CategorieMime categoria;

    @Column(name = "ETICHETTA", nullable = false, length = 50)
    private String etichetta;

    @Column(name = "MIME", nullable = false, length = 100)
    private String mime;

    @Column(name = "EXTENSION", length = 5)
    private String extension;

}