package org.kps.pub.image.hub.ui.login;

import org.kps.pub.image.hub.ui.common.Constants;
import org.kps.pub.image.hub.ui.login.model.UsersLoginMetaData;
import org.kps.pub.image.hub.ui.security.model.OAuthAttributes;
import org.kps.pub.image.hub.ui.security.model.PortalOAuth2User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Login Service 클래스
 *
 * @author kjhoon
 * @version 1.0
 * @since 2021.03.16
 **/
@Service
public class LoginService {

    /**
     * 현재 로그인된 Users Details MetaData 조회 (Get Login Meta-Information of currently logged in users)
     *
     * @return the UsersLoginMetaData
     */
    public UsersLoginMetaData getAuthenticationUserMetaData() {
        UsersLoginMetaData usersLoginMetaData = null;
        try {
            PortalOAuth2User portalOAuth2User  = (PortalOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            usersLoginMetaData = portalOAuth2User.getUsersLoginMetaData();
        } catch (NullPointerException e) {
            return null;
        }

        return usersLoginMetaData;
    }


    /**
     * Users Details MetaData 객체 생성(Create Users Login Meta-Information Object)
     *
     * @param attributes the OAuthAttributes
     */
    public UsersLoginMetaData setAuthDetailsLoginMetaData(OAuthAttributes attributes) {
        UsersLoginMetaData usersLoginMetaData = new UsersLoginMetaData();
        usersLoginMetaData.setUserId(attributes.getUsername());
        usersLoginMetaData.setUserRealName(attributes.getUsername());
        usersLoginMetaData.setUserAuthId(attributes.getSub());
        usersLoginMetaData.setActive(Constants.CHECK_Y);

        if (attributes.getAttributes().get("email") != null) {
            usersLoginMetaData.setUserEmail(attributes.getAttributes().get("email").toString());
        } else {
            usersLoginMetaData.setUserEmail("");
        }

        return usersLoginMetaData;
    }

    /**
     * 현재 로그인된 Users Details MetaData 업데이트 (Update Login Meta-Information of currently logged in users)
     *
     * @return the UsersLoginMetaData
     */
    public void updateAuthenticationUserMetaData(UsersLoginMetaData usersLoginMetaData) {
        ((PortalOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setUsersLoginMetaData(usersLoginMetaData);
    }
}