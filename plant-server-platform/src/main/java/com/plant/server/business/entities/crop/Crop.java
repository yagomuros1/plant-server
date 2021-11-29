package com.plant.server.business.entities.crop;

import com.plant.server.business.entities.category.Category;
import com.plant.server.business.entities.difficulty.Difficulty;
import com.plant.server.business.entities.situation.Situation;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "crop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "conservation")
    private String conservation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

    @ManyToMany(cascade = { CascadeType.DETACH })
    @JoinTable(
            name = "crop_category",
            joinColumns = { @JoinColumn(name = "crop_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private Set<Category> categories;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situation_id")
    private Situation situation;

    @Version
    @Column(name = "version")
    private Long version;

}
