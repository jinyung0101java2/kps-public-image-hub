package org.kps.pub.image.hub.ui.intro;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.kps.pub.image.hub.ui.common.RestTemplateService;
import org.kps.pub.image.hub.ui.security.model.OAuthTokens;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Intro Overview Controller 클래스
 *
 * @author jjy
 * @version 1.0
 * @since 2021.05.06
 */
@Controller
public class IntroOverviewController {

    private final RestTemplateService restTemplateService;

    public IntroOverviewController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    /**
     * index 페이지 이동(Move Intro overview page)
     *
     * @return the view
     */
    @GetMapping(value = {"/", ConstantsUrl.URI_CP_BASE_URL})
    public Object baseView(Model model) {
        OAuthTokens oAuthTokens = restTemplateService.getKeyCloakToken();
        model.addAttribute("accessToken", oAuthTokens.getAccessToken());

        return "images/overview";
    }

}

