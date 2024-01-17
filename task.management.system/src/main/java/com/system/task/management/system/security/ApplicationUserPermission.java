package com.system.task.management.system.security;

public enum ApplicationUserPermission {
     BUSINESS_READ("business:read"),
     BUSINESS_WRITE("business:write");


     private final String permission;
     ApplicationUserPermission(String permission) {
          this.permission=permission;
     }

     public String getPermission() {
          return permission;
     }
}
