package com.plant.server.business.entities.companion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "companion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Companion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "external_id")
    private Long externalId;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Version
    @Column(name = "version")
    private Long version;

}
