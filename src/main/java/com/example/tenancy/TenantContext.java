package com.example.tenancy;

import java.util.HashMap;
import java.util.Map;

public class TenantContext {
    private static ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

    private static Map<String, String> tenants;
    static {
        tenants = new HashMap<>();
        tenants.put("dev", "example");

    }

    public static String getTenantDb() {
        return tenants.get(getCurrentTenant()) == null ? "example" : tenants.get(getCurrentTenant());
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.set(null);
    }
}
