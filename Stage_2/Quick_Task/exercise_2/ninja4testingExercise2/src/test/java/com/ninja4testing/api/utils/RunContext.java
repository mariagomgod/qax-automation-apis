package com.ninja4testing.api.utils;

public final class RunContext {
    private static String userName;
    private static String password;
    private static String userId;
    private static String token;

    private RunContext() {}

    public static boolean hasCreds()   { return userName != null && password != null; }
    public static boolean hasUserId()  { return userId   != null; }
    public static boolean hasToken()   { return token    != null; }

    public static String getUserName() { return userName; }
    public static String getPassword() { return password; }
    public static String getUserId()   { return userId; }
    public static String getToken()    { return token; }

    public static void setCreds(String u, String p) { userName = u; password = p; }
    public static void setUserId(String id)         { userId = id; }
    public static void setToken(String t)           { token  = t;  }

    public static void reset() { userName = password = userId = token = null; }

    public static String orUserName(String local) { return local != null ? local : userName; }
    public static String orPassword(String local) { return local != null ? local : password; }
    public static String orUserId(String local)   { return local != null ? local : userId; }
    public static String orToken(String local)    { return local != null ? local : token; }

}
