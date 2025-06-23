package org.kps.pub.image.hub.ui.images.artifacts;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Artifacts Controller 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.07.01
 */
@Controller
public class ArtifactsController {
    private static final String BASE_URL = "images/artifacts/";

    /**
     * Artifacts 목록 페이지 이동(Go to the image artifacts list page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_ARTIFACT)
    public String getArtifactsList() {
        return BASE_URL + "artifacts";
    }

    /**
     * Artifacts 상세 페이지 이동(Go to the image artifacts details page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_ARTIFACT + ConstantsUrl.URI_CP_DETAILS)
    public String getArtifactsDetail() {
        return BASE_URL + "artifactsDetail";
    }

    /**
     * Artifacts 생성 페이지 이동(Go to the image artifacts creates page)
     *
     * @return the view
     */
    @PreAuthorize("@authSecurity.checkIsSuperAdmin()")
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_ARTIFACT + ConstantsUrl.URI_CP_CREATE)
    public String getArtifactsCreate() {
        return BASE_URL + "artifactsCreate";
    }
}
