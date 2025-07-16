package org.kps.pub.image.hub.ui.security;

import lombok.RequiredArgsConstructor;
import org.kps.pub.image.hub.ui.common.CommonUtils;
import org.kps.pub.image.hub.ui.common.Constants;
import org.kps.pub.image.hub.ui.common.PropertyService;
import org.kps.pub.image.hub.ui.common.model.ResultStatus;
import org.kps.pub.image.hub.ui.login.LoginService;
import org.kps.pub.image.hub.ui.login.ProviderService;
import org.kps.pub.image.hub.ui.login.model.AuthenticationResponse;
import org.kps.pub.image.hub.ui.login.model.Users;
import org.kps.pub.image.hub.ui.login.model.UsersLoginMetaData;
import org.kps.pub.image.hub.ui.security.model.OAuthAttributes;
import org.kps.pub.image.hub.ui.security.model.PortalOAuth2User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PortalOAuth2UserService implements OAuth2UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PortalOAuth2UserService.class);

    @Autowired
    private ProviderService providerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PropertyService propertyService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        OAuthAttributes attributes = new OAuthAttributes(oAuth2User.getAttributes());

        List<SimpleGrantedAuthority> roles = null;
        UsersLoginMetaData usersLoginMetaData = null;
        usersLoginMetaData = loginService.setAuthDetailsLoginMetaData(attributes);
        roles = Arrays.asList(new SimpleGrantedAuthority(attributes.getRoles().get(1)));

        return new PortalOAuth2User(roles, attributes.getAttributes(), attributes.getNameAttributeKey(), usersLoginMetaData);
    }
}
