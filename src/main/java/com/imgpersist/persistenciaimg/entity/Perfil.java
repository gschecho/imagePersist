package com.imgpersist.persistenciaimg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
        name="tbl_profile",
        schema = "db_persist_images",
        indexes = {@Index(name = "name_index", columnList = "name",unique = true)} // crea un indice para los nombres
)
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

/*
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "imagen", column = @Column(name = "profile_img", length = 1000))
    })*/
    @Lob // Permite trabajar con objetos pesados.
    @Column(name = "img_data", length = 1000)
    @Basic(optional = false, fetch = FetchType.LAZY)
    Byte[] imagen;
}
