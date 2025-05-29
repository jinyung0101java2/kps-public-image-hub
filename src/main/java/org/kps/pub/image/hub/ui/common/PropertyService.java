package org.kps.pub.image.hub.ui.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Property Service 클래스
 *
 * @author jjy
 * @version 1.0
 * @since 2020.08.25
 */
@Service
@Data
public class PropertyService {

    @Value("${cpApi.url}")
    private String cpApiUrl;

    @Value("${private.repository.url}")
    private String privateRepositoryUrl;

    @Value("${keycloak.superAdminRole}")
    private String keycloakSuperAdminRole;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String keycloakClientId;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String keycloakClientSecret;

    @Value("${keycloak.oauth2LoginPath}")
    private String keycloakOauth2LoginPath;

    @Value("${keycloak.logoutUri}")
    private String keycloakLogoutUri;

    @Value("${keycloak.introspectUri}")
    private String keycloakIntrospectUri;

    @Value("${server.servlet.session.cookie.name}")
    private String cpSessionCookieName;

}