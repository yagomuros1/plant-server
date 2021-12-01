package com.plant.server.web.controller.api;

import com.plant.server.business.services.test.cos.TestCO;
import com.plant.server.commons.properties.CommonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;

@CrossOrigin
@RestController
@RequestMapping(ApiURL.BASE + ApiURL.PUBLIC)
@Slf4j
public class ApiPublicController {

    @Autowired
    private CommonProperties commonProperties;

    @GetMapping(path = ApiURL.TEST, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public static ResponseEntity<TestCO> testV101() {
        log.debug("api test 1.0.1");
        return ResponseEntity.ok().body(new TestCO("test 1.0.1"));
    }

    @GetMapping(path = ApiURL.TEST, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_2)
    public static ResponseEntity<TestCO> testV102() {
        log.debug("api test 1.0.2");
        return ResponseEntity.ok().body(new TestCO("test 1.0.2"));
    }

    @GetMapping(path = ApiURL.TEST, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_3)
    public static ResponseEntity<TestCO> testV103() {
        log.debug("api test 1.0.3");
        return ResponseEntity.ok().body(new TestCO("test (CI working)"));
    }

    @SuppressWarnings("static-method")
    @GetMapping(path = ApiURL.TEST)
    public ResponseEntity<TestCO> testDefault() {
        log.debug("api test default");
        return testV103();
    }

    @GetMapping(path = ApiURL.VERSION, produces = ApiUtils.TYPE_CMG_JSON_V_1_0_0)
    public ResponseEntity<AbstractMap.SimpleEntry<String, String>> version() {
        log.debug("api version");
        return ResponseEntity.ok().body(new AbstractMap.SimpleEntry<>("version", this.commonProperties.getVersion()));
    }

}
