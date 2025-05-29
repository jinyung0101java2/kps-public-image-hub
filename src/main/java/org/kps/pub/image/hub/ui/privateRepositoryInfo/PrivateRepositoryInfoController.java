package org.kps.pub.image.hub.ui.privateRepositoryInfo;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.kps.pub.image.hub.ui.common.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Private Registry Controller 클래스
 *
 * @author jjy
 * @version 1.0
 * @since 2022.07.13
 */

@Controller
public class PrivateRepositoryInfoController {
    private static final String BASE_URL = "privateRepositoryInfo/";
    private final PropertyService propertyService;

    @Autowired
    public PrivateRepositoryInfoController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    /**
     * Private Repository Info 페이지 이동(Go to the Private Repository Info page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_INFO_PRIVATE_REPOSITORY)
    public String getPrivateRepositoryInfo(Model model) {
        model.addAttribute("privateRepositoryUrl", propertyService.getPrivateRepositoryUrl());
        return BASE_URL + "privateRepositoryInfo";
    }
}