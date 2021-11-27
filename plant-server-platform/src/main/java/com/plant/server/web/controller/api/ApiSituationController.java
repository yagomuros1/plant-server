package com.plant.server.web.controller.api;

import com.plant.server.business.services.situation.SituationService;
import com.plant.server.business.services.situation.cos.SituationCO;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.collection.Chunk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(ApiURL.BASE + ApiURL.PUBLIC)
@Slf4j
public class ApiSituationController {

    @Autowired
    private SituationService situationService;

    @Autowired
    private CommonProperties commonProperties;

    @GetMapping(path = ApiURL.SITUATIONS, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<Chunk<SituationCO>> getSituationTypes(@RequestParam(name = ApiURL.PAGE_PARAM) Optional<Integer> page) {
        return ResponseEntity.ok().body(this.situationService.getSituations(page));
    }

}
