package org.kps.pub.image.hub.ui.images.logs;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Logs Controller 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.07.01
 */

@Controller
public class LogsController {
    private static final String BASE_URL = "images/logs/";

    /**
     * Logs 목록 페이지 이동(Go to the image logs list page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_LOG)
    public String getLogsList() {
        return BASE_URL + "logs";
    }

}
