package com.plant.server.business.entities.difficulty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "difficulty")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Difficulty implements Serializable {

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
