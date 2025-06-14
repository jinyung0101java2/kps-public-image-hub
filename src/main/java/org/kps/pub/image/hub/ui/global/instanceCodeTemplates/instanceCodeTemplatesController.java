package org.kps.pub.image.hub.ui.global.instanceCodeTemplates;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * InstanceCodeTemplates Controller 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.07.01
 */

@PreAuthorize("@authSecurity.checkIsGlobal()")
@Controller
public class instanceCodeTemplatesController {
    private static final String BASE_URL = "global/instanceCodeTemplates/";

    /**
     * InstanceCodeTemplates 목록 페이지 이동(Go to the templates list page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_GLOBAL_INSTANCE_CODE_TEMPLATES)
    public String getInstanceCodeTemplatesList() {
        return BASE_URL + "instanceCodeTemplates";
    }

    /**
     * InstanceCodeTemplates 상세 페이지 이동(Go to the templates details page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_GLOBAL_INSTANCE_CODE_TEMPLATES + ConstantsUrl.URI_CP_DETAILS)
    public String getInstanceCodeTemplatesDetail() {
        return BASE_URL + "instanceCodeTemplatesDetail";
    }

    /**
     * InstanceCodeTemplates 생성 페이지 이동(Go to the templates creates page)
     *
     * @return the view
     */
    @PreAuthorize("@authSecurity.checkIsSuperAdmin()")
    @GetMapping(value = ConstantsUrl.URI_CP_GLOBAL_INSTANCE_CODE_TEMPLATES + ConstantsUrl.URI_CP_CREATE)
    public String getInstanceCodeTemplatesCreate() {
        return BASE_URL + "instanceCodeTemplatesCreate";
    }
}
