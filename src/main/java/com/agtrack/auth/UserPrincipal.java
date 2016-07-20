package com.agtrack.auth;


import org.glassfish.jersey.internal.util.Base64;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.security.Principal;
import java.util.LinkedHashMap;

@XmlRootElement
public class UserPrincipal implements Principal, Serializable {

    private String user = "";
    private String fullName = "";
    private String email = "";
    private String password = "";
    private String basicAuthorization = "";


    public UserPrincipal() {
    }

    public UserPrincipal(LinkedHashMap<String, String> user) {
        this.setUser(user.get("user"));
        this.setFullName(user.get("fullName"));
        this.setEmail(user.get("email"));
        this.setBasicAuthorization(user.get("basicAuthorization"));
    }

    @Override
    public String getName() {
        return this.user;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBasicAuthorization() {
        return basicAuthorization;
    }

    public void setBasicAuthorization(String basicAuthorization) {
        this.basicAuthorization = basicAuthorization;
    }

}
