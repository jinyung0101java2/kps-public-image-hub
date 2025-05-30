package org.kps.pub.image.hub.ui.common;

/**
 * Constants 클래스
 *
 * @author kjhoon
 * @version 1.0
 * @since 2021.06.14
 */
public class ConstantsUrl {
    public static final String URI_CP_BASE_URL = "/hub";
    public static final String URI_CP_GLOBAL_URL = "/hub";
    public static final String URI_CP_INDEX_URL = "/hub/clusters";
    public static final String URI_CP_CLUSTERS_NAMESPACES = "/hub/namespaces";
    public static final String URI_CP_CLUSTERS_NODES = "/hub/nodes";

    public static final String URI_CP_WORKLOADS_DEPLOYMENTS = "/hub/deployments";
    public static final String URI_CP_WORKLOADS_PODS = "/hub/pods";
    public static final String URI_CP_WORKLOADS_REPLICASETS = "/hub/replicaSets";

    public static final String URI_CP_SERVICES_SERVICES = "/hub/services";
    public static final String URI_CP_SERVICES_INGRESSES = "/hub/ingresses";

    public static final String URI_CP_STORAGES_STORAGECLASSES = "/hub/storageClasses";
    public static final String URI_CP_STORAGES_PERSISTENTVOLUMES = "/hub/persistentVolumes";
    public static final String URI_CP_STORAGES_PERSISTENTVOLUMECLAIMS = "/hub/persistentVolumeClaims";

    public static final String URI_CP_MANAGEMENTS_LIMITRANGES = "/hub/limitRanges";
    public static final String URI_CP_MANAGEMENTS_RESOURCEQUOTAS = "/hub/resourceQuotas";
    public static final String URI_CP_MANAGEMENTS_ROLES = "/hub/roles";
    public static final String URI_CP_MANAGEMENTS_USERS_ADMIN = "/hub/admin";
    public static final String URI_CP_MANAGEMENTS_USERS = "/hub/users";
    public static final String URI_CP_MANAGEMENTS_INACTIVE_USERS = "/hub/inactiveUsers";
    public static final String URI_CP_CONFIGS_CONFIGMAPS = "/hub/configMaps";
    public static final String URI_CP_CONFIGS_SECRETS = "/hub/secrets";
    public static final String URI_CP_CONFIGS_VAULT_SECRETS = "/hub/vaultSecrets";

    public static final String URI_CP_CHAOS_EXPERIMENTS = "/hub/experiments";
    public static final String URI_CP_CHAOS_EVENTS = "/hub/events";
    public static final String URI_CP_INFO_ACCESS = "/hub/access";
    public static final String URI_CP_INFO_PRIVATE_REPOSITORY = "/hub/private_repository";

    public static final String URI_CP_GLOBAL_CLUSTERS = "/hub/global/clusters";
    public static final String URI_CP_IMAGE_PROJECTS = "/hub/image/projects";
    public static final String URI_CP_GLOBAL_CLOUD_ACCOUNTS = "/hub/global/cloudAccounts";
    public static final String URI_CP_GLOBAL_INSTANCE_CODE_TEMPLATES = "/hub/global/templates";
    public static final String URI_CP_GLOBAL_SSH_KEYS = "/hub/global/sshKeys";

    public static final String URI_CP_GLOBAL_ = "/hub/global/templates";


    public static final String URI_CP_CATALOG_REPOSITORIES = "/hub/catalog/repositories";
    public static final String URI_CP_CATALOG_RELEASES = "/hub/catalog/releases";
    public static final String URI_CP_CATALOG_CHARTS = "/hub/charts";

    public static final String URI_CP_CATALOG_INSTALL = "/hub/install";

    public static final String URI_CP_LIST = "/hub/list";
    public static final String URI_CP_DETAILS = "/hub/details";
    public static final String URI_CP_VAULT_DETAILS = "/hub/vault/details";
    public static final String URI_CP_CREATE = "/hub/create";
    public static final String URI_CP_UPDATE = "/hub/update";
    public static final String URI_CP_POPUP = "/hub/popup";
    public static final String URI_CP_LOGS = "/hub/logs";

    public static final String URI_CP_ADD = "/hub/add";
    public static final String URI_CP_UPGRADE = "/hub/upgrade";
    public static final String URI_CP_GET_USER_LOGIN_DATA = "/hub/userLoginData";
    public static final String URI_CP_REFRESH_TOKEN = "/hub/refreshToken";

    public static final String URI_CP_SESSION_OUT = "/sessionout";
    public static final String URI_AUTHENTICATION_FAILED = "/error/authenticationFailed";
    public static final String URI_CP_LOGOUT ="/logout";
    public static final String URl_CP_INACTIVE = "/inactive";

    public static final String URI_API_SET_CLUSTER_AUTHORITY="/setAuthority";

    //CP-API REQUEST URI
    public static final String URL_API_LOGIN = "/login";
    public static final String URL_API_SIGNUP = "/signUp";

    public static final String URI_API_REFRESH_TOKEN = "/refreshtoken";

    //LOCALE LANGUAGE
    public static final String URL_API_LOCALE_LANGUAGE = "/localeLanguage";
    public static final String URL_API_CHANGE_LOCALE_PARAM = "language";
    public static final String LANG_KO = "ko";
    public static final String LANG_KO_START_WITH = "ko_";
    public static final String LANG_EN = "en";
}