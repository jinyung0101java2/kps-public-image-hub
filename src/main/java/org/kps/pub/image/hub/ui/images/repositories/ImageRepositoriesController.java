package org.kps.pub.image.hub.ui.images.repositories;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ImageRepositories Controller 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.07.01
 */

@Controller
public class ImageRepositoriesController {
    private static final String BASE_URL = "images/repositories/";

    /**
     * ImageRepositories 목록 페이지 이동(Go to the image repositories list page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_REPOSITORY)
    public String getImageRepositoriesList() {
        return BASE_URL + "repositories";
    }

    /**
     * ImageRepositories 상세 페이지 이동(Go to the image repositories details page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_REPOSITORY + ConstantsUrl.URI_CP_DETAILS)
    public String getImageRepositoriesDetail() {
        return BASE_URL + "repositoriesDetail";
    }

    /**
     * ImageRepositories 생성 페이지 이동(Go to the image repositories creates page)
     *
     * @return the view
     */
    @PreAuthorize("@authSecurity.checkIsSuperAdmin()")
    @GetMapping(value = ConstantsUrl.URI_KPS_HUB_IMAGE_REPOSITORY + ConstantsUrl.URI_CP_CREATE)
    public String getImageRepositoriesCreate() {
        return BASE_URL + "repositoriesCreate";
    }

}
