package org.container.platform.web.ui.catalogs.repositories;

import org.container.platform.web.ui.common.ConstantsUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Repositories Controller 클래스
 *
 * @author kjhoon
 * @version 1.0
 * @since 2024.04.17
 */

@Controller
public class RepositoriesController {
    private static final String BASE_URL = "catalogs/repositories/";


    /**
     * Repositories 목록 페이지 이동(Go to the repositories list page)
     *
     * @return the view
     */@GetMapping(value = ConstantsUrl.URI_CP_CATALOG_REPOSITORIES)
    public String getRepoList() {
        return BASE_URL + "listRepositories";
    }

    /**
     * Repositories 추가 페이지 이동(Go to the repositories add page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_CATALOG_REPOSITORIES + ConstantsUrl.URI_CP_ADD)
    public String addRepo() {
        return BASE_URL + "addRepositories";
    }


    /**
     * Repositories 차트 목록 페이지 이동(Go to the repositories chart list page)
     *
     * @return the view
     */
    @GetMapping(value = ConstantsUrl.URI_CP_CATALOG_REPOSITORIES + ConstantsUrl.URI_CP_CATALOG_CHARTS)
    public String getRepoChartList() {
        return BASE_URL + "listCharts";
    }

}

