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

    /**
     * Logs 상세 페이지 이동(Go to the image logs details page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_LOG + ConstantsUrl.URI_CP_DETAILS)
    public String getLogsDetail() {
        return BASE_URL + "logsDetail";
    }

    /**
     * Logs 생성 페이지 이동(Go to the image logs creates page)
     *
     * @return the view
     */
    @PreAuthorize("@authSecurity.checkIsSuperAdmin()")
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_LOG + ConstantsUrl.URI_CP_CREATE)
    public String getLogsCreate() {
        return BASE_URL + "logsCreate";
    }

}
