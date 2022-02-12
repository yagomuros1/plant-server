package com.plant.server.business.entities.singleCrop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "single_crop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SingleCrop implements Serializable {

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

    // Used to prioritize in TOP crops list
    @Column(name = "topPriority")
    private Long topPriority;

    @Version
    @Column(name = "version")
    private Long version;

}
