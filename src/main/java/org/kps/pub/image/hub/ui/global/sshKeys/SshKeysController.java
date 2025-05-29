package org.kps.pub.image.hub.ui.global.sshKeys;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * SshKeys Controller 클래스
 *
 * @author jjy
 * @version 1.0
 * @since 2023.12.22 */

@PreAuthorize("@authSecurity.checkIsGlobal()")
@Controller
public class SshKeysController {
    private static final String BASE_URL = "global/sshKeys/";

    /**
     * SshKeys 목록 페이지 이동(Go to the ssh keys list page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_GLOBAL_SSH_KEYS)
    public String getSshKeysList() {
        return BASE_URL + "sshKeys";
    }

    /**
     * SshKeys 상세 페이지 이동(Go to the ssh keys details page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_GLOBAL_SSH_KEYS + ConstantsUrl.URI_CP_DETAILS)
    public String getSshKeysDetails() {
        return BASE_URL + "sshKeysDetail";
    }

    /**
     * SshKeys 생성 페이지 이동(Go to the ssh keys creates page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_GLOBAL_SSH_KEYS + ConstantsUrl.URI_CP_CREATE)
    public String getKSshKeysCreate() {
        return BASE_URL + "sshKeysCreate";
    }
}
