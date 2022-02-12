package com.plant.server.web.controller.api;

import com.plant.server.business.services.singleCrop.SingleCropService;
import com.plant.server.business.services.singleCrop.cos.SingleCropCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.Chunk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(ApiURL.BASE + ApiURL.PUBLIC)
@Slf4j
public class ApiSingleCropRepository {

    @Autowired
    private SingleCropService singleCropService;

    @Autowired
    private CommonProperties commonProperties;

    @GetMapping(path = ApiURL.SINGLE_CROPS, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<Chunk<SingleCropCO>> getSingleCrops(@RequestParam(name = ApiURL.PAGE_PARAM) Optional<Integer> page) {
        return ResponseEntity.ok().body(this.singleCropService.getSingleCrops(page));
    }

    @GetMapping(path = ApiURL.TOP_CROPS, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<List<SingleCropCO>> getTopCrops() {
        return ResponseEntity.ok().body(this.singleCropService.getTopCrops());
    }
}