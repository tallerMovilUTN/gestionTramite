package com.gestion.tramite.controller;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gestion.tramite.entidad.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @PostMapping("user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        //String token = getJWTToken(username);
        User user = new User();
        user.setUser(username);
        //user.setToken(token);
        user.setToken("token");
        return user;

    }

    /**private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }**/
}