package com.plant.server.web.controller.api;

import com.plant.server.business.services.difficulty.DifficultyService;
import com.plant.server.business.services.difficulty.cos.DifficultyCO;
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
public class ApiDifficultyController {

    @Autowired
    private DifficultyService difficultyService;

    @Autowired
    private CommonProperties commonProperties;

    @GetMapping(path = ApiURL.DIFFICULTIES, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<Chunk<DifficultyCO>> getDifficulties(@RequestParam(name = ApiURL.PAGE_PARAM) Optional<Integer> page) {
        return ResponseEntity.ok().body(this.difficultyService.getDifficulties(page));
    }

}