package org.kps.pub.image.hub.ui.images.vulnerabilities;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * Vulnerabilities Controller 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.07.01
 */

@Controller
public class VulnerabilitiesController {
    private static final String BASE_URL = "images/vulnerabilities/";

    /**
     * Vulnerabilities 목록 페이지 이동(Go to the vulnerabilities list page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_VULNERABILITY)
    public String getVulnerabilitiesList() {
        return BASE_URL + "vulnerabilities";
    }

}
