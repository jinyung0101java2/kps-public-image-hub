package org.kps.pub.image.hub.ui.services.ingresses;

import org.kps.pub.image.hub.ui.common.ConstantsUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Ingresses Controller 클래스
 *
 * @author jjy
 * @version 1.0
 * @since 2022.05.17
 */
@Controller
public class IngressesController {

    private static final String BASE_URL = "ingresses/";

    /**
     * Ingresses 목록 페이지 이동(Go to the ingresses list page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_SERVICES_INGRESSES )
    public String getIngressesList(){
        return BASE_URL + "ingresses";}

    /**
     * Ingresses 생성 페이지 이동(Go to the ingresses create page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_SERVICES_INGRESSES + ConstantsUrl.URI_CP_CREATE)
    public String createIngresses() {
        return BASE_URL + "ingressesCreate";
    }


    /**
     * Ingresses 상세 페이지 이동(Go to the ingresses details page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_SERVICES_INGRESSES + ConstantsUrl.URI_CP_DETAILS)
    public String getIngressesDetails(){return
            BASE_URL + "ingressesDetail";}

    /**
     * Ingresses 수정 페이지 이동(Go to the ingresses update page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_SERVICES_INGRESSES + ConstantsUrl.URI_CP_UPDATE)
    public String updateIngresses() {
        return BASE_URL + "ingressesUpdate";
    }
}
