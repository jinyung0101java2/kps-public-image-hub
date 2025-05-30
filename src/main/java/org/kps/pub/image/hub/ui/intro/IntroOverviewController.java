package org.kps.pub.image.hub.ui.intro;

import org.kps.pub.image.hub.ui.common.Constants;
import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.kps.pub.image.hub.ui.login.LoginService;
import org.kps.pub.image.hub.ui.login.model.UsersLoginMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private LoginService loginService;

    /**
     * index 페이지 이동(Move Intro overview page)
     *
     * @return the view
     */
    @GetMapping(value = {"/", ConstantsUrl.URI_CP_BASE_URL})
    public Object baseView() {
        UsersLoginMetaData usersLoginMetaData = loginService.getAuthenticationUserMetaData();
        if (Constants.AUTH_ADMIN_LIST.contains(usersLoginMetaData.getUserType())) {
            return "images/overview";
        }

        return "index";
    }


    /**
     * Index 페이지 이동(Move Intro overview page)
     *
     * @return the intro overview
     */
    @GetMapping(value = ConstantsUrl.URI_CP_INDEX_URL)
    public String getIntroOverview() {
        return "index";
    }
}

