package com.plant.server.business.entities.property;

import com.plant.server.business.entities.crop.Crop;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "property")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "value")
    private String value;

    @ManyToMany(mappedBy = "properties")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Crop> crops;

    @Version
    @Column(name = "version")
    private Long version;

}