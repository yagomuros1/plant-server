package com.plant.server.business.entities.cropProperty;

import com.plant.server.business.entities.category.Category;
import com.plant.server.business.entities.crop.Crop;
import com.plant.server.business.entities.property.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crop_property")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropProperty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    @Version
    @Column(name = "version")
    private Long version;

}