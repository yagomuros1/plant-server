package com.plant.server.business.entities.cropCategory;

import com.plant.server.business.entities.category.Category;
import com.plant.server.business.entities.crop.Crop;
import com.plant.server.business.entities.situation.Situation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crop_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Version
    @Column(name = "version")
    private Long version;

}