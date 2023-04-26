package com.prometheus;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import java.util.HashMap;
import java.util.Map;

public class BaseContextHandler
{
    public static final String CONTEXT_KEY_ACCOUNT_ID = "currentAccountId";
    public static final String CONTEXT_KEY_SYSTEM_ID = "systemId";
    public static final String CONTEXT_KEY_AUTHENTICATION_ID = "authenticationId";
    public static final String CONTEXT_KEY_USER_NAME = "currentUserName";
    public static final String CONTEXT_KEY_LOGIN_NAME = "currentLoginName";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String CONTEXT_KEY_USER_MODEL_ID = "currentUserModelId";
    public static final String CONTEXT_KEY_USER_ISMANAGER = "currentUserIsManager";
    public static final String IS_REFRESH_TOKEN = "is_refresh_token";
    public static final String CONTEXT_KEY_REQUEST_TYPE = "currentType";
    private static final String CONTEXT_KEY_USER_REFRESH_TOKEN = "currentRefreshToken";
    public static ThreadLocal<Map<String, Object>> threadLocal = new InheritableThreadLocal();

    public BaseContextHandler() {
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = (Map)threadLocal.get();
        if (map == null) {
            map = new HashMap();
            threadLocal.set(map);
        }

        ((Map)map).put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = (Map)threadLocal.get();
        if (map == null) {
            map = new HashMap();
            threadLocal.set(map);
        }

        return ((Map)map).get(key);
    }

    public static void setAccountId(String accountId) {
        set("currentAccountId", accountId);
    }

    public static String getAccountId() {
        Object value = get("currentAccountId");
        return StrUtil.nullToEmpty(StrUtil.utf8Str(value));
    }

    public static void setAuthenticationId(String authenticationId) {
        set("authenticationId", authenticationId);
    }

    public static Long getAuthenticationId() {
        Object value = get("authenticationId");
        if (ObjectUtil.isNull(value)) {
            throw new RuntimeException("请求authenticationId为空！");
        } else {
            return Long.valueOf(StrUtil.utf8Str(value));
        }
    }

    public static void setSystemId(String systemId) {
        set("systemId", systemId);
    }

    public static Long getSystemId() {
        Object value = get("systemId");
        return ObjectUtil.isNull(value) ? null : Long.valueOf(StrUtil.utf8Str(value));
    }

    public static void setUserName(String userName) {
        set("currentUserName", userName);
    }

    public static String getUserName() {
        Object value = get("currentUserName");
        return StrUtil.nullToEmpty(StrUtil.utf8Str(value));
    }

    public static void setLoginName(String loginName) {
        set("currentLoginName", loginName);
    }

    public static String getLoginName() {
        Object value = get("currentLoginName");
        return StrUtil.nullToEmpty(StrUtil.utf8Str(value));
    }

    public static void setToken(String token) {
        set("currentUserToken", token);
    }

    public static void setRefreshToken(String token) {
        set("currentRefreshToken", token);
    }

    public static String getToken() {
        Object value = get("currentUserToken");
        return StrUtil.nullToEmpty(StrUtil.utf8Str(value));
    }

    public static String getRefreshToken() {
        Object value = get("currentRefreshToken");
        return StrUtil.nullToEmpty(StrUtil.utf8Str(value));
    }

    public static void setModelId(String modelId) {
        set("currentUserModelId", modelId);
    }

    public static String getModelId() {
        Object value = get("currentUserModelId");
        return StrUtil.nullToEmpty(StrUtil.utf8Str(value));
    }

    public static void setIsManager(String isManager) {
        set("currentUserIsManager", isManager);
    }

    public static String getIsManager() {
        Object value = get("currentUserIsManager");
        return StrUtil.nullToEmpty(StrUtil.utf8Str(value));
    }

    public static void setIsRefreshToken(boolean isRefreshToken) {
        set("is_refresh_token", isRefreshToken);
    }

    public static boolean isRefreshToken() {
        Object value = get("is_refresh_token");
        return value == null ? false : Boolean.valueOf(StrUtil.utf8Str(value));
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static Map<String, Object> getAttributes() {
        return (Map)threadLocal.get();
    }

    public static void setAttributes(Map<String, Object> attributes) {
        if (attributes == null) {
            threadLocal.remove();
        }

        threadLocal.set(attributes);
    }
}
