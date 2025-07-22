package org.kps.pub.image.hub.ui.harbor;

import org.kps.pub.image.hub.ui.common.PropertyService;
import org.kps.pub.image.hub.ui.common.RestTemplateService;
import org.springframework.http.HttpMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * Harbor Login Controller 클래스
 *
 * @author jjy
 * @version 1.0
 * @since 2021.05.06
 */
@RestController
public class HarborLoginController {

    private final RestTemplateService restTemplateService;
    private final PropertyService propertyService;

    public HarborLoginController(RestTemplateService restTemplateService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.propertyService = propertyService;
    }

    /**
     * index 페이지 이동(Move Intro overview page)
     *
     * @return the view
     */
    @GetMapping(value = "/harbor/login")
    public Object harborLoginCheck(Model model) {
        Object obj = restTemplateService.sendLoginHarbor(propertyService.getHarborDomainUrl(), HttpMethod.GET, null, String.class);
        System.out.println(obj);

        return obj;
    }

}

