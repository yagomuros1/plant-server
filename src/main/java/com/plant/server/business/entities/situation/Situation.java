package com.plant.server.business.entities.situation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "situation")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Situation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Version
    @Column(name = "version")
    private Long version;

}