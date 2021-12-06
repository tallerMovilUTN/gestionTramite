package com.gestion.tramite.controller;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gestion.tramite.entidad.Documento;
import com.gestion.tramite.entidad.User;
import com.gestion.tramite.service.DocumentoService;
import com.gestion.tramite.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;


@CrossOrigin( origins = "*" , methods = { RequestMethod. GET , RequestMethod. POST,RequestMethod.PUT })
@RestController
@RequestMapping(value = "/usuario")
public class UserController {

    @Autowired
    private UserService service;

    //@CrossOrigin(origins = "http://localhost:4200")
    //@PostMapping("user")
    @PostMapping
    //public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
    public User login(@RequestBody User a1) {
        User user = null;
        if ((a1.getUser().equals("admin")) && (a1.getPassword().equals("a1")))
        {
            String token = getJWTToken(a1.getUser());
            user = new User();
            user.setUser(a1.getUser());
            user.setPassword(a1.getPassword());
            user.setToken(token);
            service.updateUser(user);
            //user.setToken("token");
        }
        return user;
    }



    @GetMapping(value = "/{user}")
    public ResponseEntity<User> getDocumento(@PathVariable("user") String user)
    {
        User a1 =  service.getUser(user);
        if (null==a1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a1);
    }


    private String getJWTToken(String username) {
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
                .setExpiration(new Date(System.currentTimeMillis() + 11600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}