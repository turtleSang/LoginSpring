package com.ThankSen.BaiTap2.JwtUnit;


import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.beans.Encoder;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtHelper {
    private String privateKey = "J7AlKlSiWtv2Wvng/hteePlh87HE9mf1yrCbn0y4JE0=";
    Gson gson = new Gson();

    public String generateJwt(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));

        String jws = Jwts.builder().setSubject(data).signWith(key).compact();

        return jws;
    }

    public Authentication decodeJwt(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jws<Claims> data = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            String dataJson = data.getBody().getSubject();
            DataToken dataToken = gson.fromJson(dataJson, DataToken.class);

            Collection<GrantedAuthority> listRoles = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority(dataToken.getRole_name());

            listRoles.add(authority);

            Authentication authentication = new UsernamePasswordAuthenticationToken(dataToken.getEmail(),"",listRoles);
            return authentication;
        }catch (JwtException e){
            System.out.println(e.getCause());
            return null;
        }
    }

}
