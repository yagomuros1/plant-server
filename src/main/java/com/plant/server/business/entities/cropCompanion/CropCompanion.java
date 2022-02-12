package com.plant.server.business.entities.cropCompanion;

import com.plant.server.business.entities.category.Category;
import com.plant.server.business.entities.companion.Companion;
import com.plant.server.business.entities.crop.Crop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crop_companion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropCompanion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companion_id")
    private Companion companion;

    @Version
    @Column(name = "version")
    private Long version;

}