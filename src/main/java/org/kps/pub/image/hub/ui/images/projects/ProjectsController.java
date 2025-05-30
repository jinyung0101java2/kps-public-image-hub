package org.kps.pub.image.hub.ui.images.projects;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Projects Controller 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.07.01
 */

@PreAuthorize("@authSecurity.checkIsGlobal()")
@Controller
public class ProjectsController {
    private static final String BASE_URL = "images/projects/";

    /**
     * Projects 목록 페이지 이동(Go to the projects list page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_IMAGE_PROJECTS)
    public String getProjectsList() {
        return BASE_URL + "projects";
    }

    /**
     * Projects 상세 페이지 이동(Go to the projects details page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_IMAGE_PROJECTS + ConstantsUrl.URI_CP_DETAILS)
    public String getProjectsDetail() {
        return BASE_URL + "projectsDetail";
    }

    /**
     * Projects 생성 페이지 이동(Go to the projects creates page)
     *
     * @return the view
     */
    @PreAuthorize("@authSecurity.checkIsSuperAdmin()")
    @GetMapping(value = ConstantsUrl.URI_CP_IMAGE_PROJECTS + ConstantsUrl.URI_CP_CREATE)
    public String getProjectsCreate() {
        return BASE_URL + "projectsCreate";
    }

}
