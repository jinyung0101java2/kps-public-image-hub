package org.kps.pub.image.hub.ui.intro;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.kps.pub.image.hub.ui.common.RestTemplateService;
import org.kps.pub.image.hub.ui.login.LoginService;
import org.kps.pub.image.hub.ui.login.model.UsersLoginMetaData;
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
    private final LoginService loginService;

    public IntroOverviewController(RestTemplateService restTemplateService, LoginService loginService) {
        this.restTemplateService = restTemplateService;
        this.loginService = loginService;
    }

    private static final String BASE_URL = "images/dashboard/";

    /**
     * index 페이지 이동(Move Intro overview page)
     *
     * @return the view
     */
    @GetMapping(value = {"/", ConstantsUrl.URI_CP_BASE_URL})
    public Object baseView(Model model) {
        OAuthTokens oAuthTokens = restTemplateService.getKeyCloakToken();
        UsersLoginMetaData usersLoginMetaData = loginService.getAuthenticationUserMetaData();
        model.addAttribute("accessToken", oAuthTokens.getAccessToken());
        model.addAttribute("username", usersLoginMetaData.getUserId());
        model.addAttribute("realname", usersLoginMetaData.getUserRealName());
//        model.addAttribute("email", usersLoginMetaData.getUserEmail());

        return "images/overview";
    }

    @GetMapping(value = ConstantsUrl.URI_DASHBOARD)
    public String getDashboard() {
        return BASE_URL + "dashboard";
    }

}

