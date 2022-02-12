package com.plant.server.business.services.crop.cos;

import com.plant.server.business.services.category.cos.CategoryCO;
import com.plant.server.business.services.singleCrop.cos.SingleCropCO;
import com.plant.server.business.services.difficulty.cos.DifficultyCO;
import com.plant.server.business.services.property.cos.PropertyCO;
import com.plant.server.business.services.situation.cos.SituationCO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CropCO implements Serializable {

    protected Long id;

    protected String name;

    protected String description;

    protected String image;

    protected String conservation;

    protected DifficultyCO difficulty;

    protected SituationCO situation;

    protected List<CategoryCO> categories;

    protected List<PropertyCO> properties;

    protected List<SingleCropCO> companions;

}
