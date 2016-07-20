package com.agtrack.auth;

import com.agtrack.utilities.ConnectionManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.wrappers.StringTrimmedResultSet;
import org.glassfish.jersey.internal.util.Base64;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ServiceAuth {


    protected static final String AUTHENTICATION_SCHEME = "JWT";
    private static final String KEY = "9c134491-b1ca-443b-97c0-8a308114aa75";

    public static boolean authenticate(String user, String password) {
        try {
            password = Base64.encodeAsString(password);
            return confirmCredentials(user, password);
        } catch (Exception e) {
            return false;
        }
    }


    private static boolean confirmCredentials(String user, String password) throws Exception{
        boolean valid = false;

        String sql = "SELECT username \n" +
                "FROM GROWER\n" +
                "WHERE username = ? and password = ?";
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, user);
            pstmt.setString(2, password);

            ResultSet rs = StringTrimmedResultSet.wrap(pstmt.executeQuery());
            if(rs.next()){
                valid = true;
            }
        }

        return valid;
    }

    public static String getToken(UserPrincipal userPrincipal) throws Exception {
        String token;

        Claims claims = new DefaultClaims();

        UserPrincipal user = retrieveUserPrincipal(userPrincipal.getName());
        user.setBasicAuthorization("Basic " +
                Base64.encodeAsString(userPrincipal.getName() + ":" + userPrincipal.getPassword()));
        claims.put("user", user);

        Calendar calendar = Calendar.getInstance();
        Date issuedAt = calendar.getTime();

        calendar.add(Calendar.HOUR, 24);
        Date expiration = calendar.getTime();

        token = Jwts.builder()
                .setIssuer("agtrack.com")
                .setSubject(userPrincipal.getName())
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();

        return token;
    }



    public static DefaultClaims parseToken(String token) throws Exception {

        if (!token.startsWith("Bearer ")) {
            throw new Exception("Invalid Authentication token.  Should be 'Bearer <JWT token>)");
        }

        token = token.substring(7);

        Jwt jwtToken = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);

        return (DefaultClaims) jwtToken.getBody();

    }


    private static UserPrincipal retrieveUserPrincipal(String name) throws Exception {

        UserPrincipal principal = null;
        String sql = "SELECT id AS ID, username AS FULL_NAME, username AS USER, email AS EMAIL " +
                "FROM GROWER " +
                "WHERE username = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name.toUpperCase());

            ResultSet rs = StringTrimmedResultSet.wrap(pstmt.executeQuery());
            if (rs.next()) {
                principal = new GenerousBeanProcessor().toBean(rs, UserPrincipal.class);
            }

        }

        return principal;

    }

}
