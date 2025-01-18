package com.sweetsavvy.app.constants;

public interface AppConstant {
    //swagger config
    String SWAGGER_TITLE = "TN-WAF";
    String SWAGGER_DESCRIPTION = "All apis of WAF";
    String SWAGGER_COMPANY_NAME = "Technonext Ltd.";
    String URL = "URL";
    String SWAGGER_VERSION = "1.0.0";
    String API_VERSION = "v1";
    Long CORS_MAX_AGE = 3600L;
    String[] WHITE_LIST_URLS = new String[]{
            "/test/**",
            "/api/login",
            "/api/register",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}
