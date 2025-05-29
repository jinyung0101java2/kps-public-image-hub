package org.kps.pub.image.hub.ui.security.handler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kps.pub.image.hub.ui.common.Constants;
import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.kps.pub.image.hub.ui.common.CustomIntercepterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class PortalOauth2SecurityFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PortalOauth2SecurityFilter.class);
    private CustomIntercepterService customIntercepterService;

    @Autowired
    public PortalOauth2SecurityFilter(CustomIntercepterService customIntercepterService) {
        this.customIntercepterService = customIntercepterService;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(Constants.AUTH_INACTIVE_USER))) {
                LOGGER.info("######## MOVE TO THE INACTIVE USER VIEW");
                response.sendRedirect(ConstantsUrl.URl_CP_INACTIVE);
                return;
            }
            if (!customIntercepterService.isActive()) {
                LOGGER.info("######## LOGOUT CAUSE OAUTH2 TOKEN IS NOT ACTIVE");
                request.getSession().invalidate();
                response.sendRedirect(ConstantsUrl.URI_CP_SESSION_OUT);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
