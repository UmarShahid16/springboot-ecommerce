//package com.evantagesoft.ecommerce.util;
//
//import com.evantagesoft.ecommerce.entity.User;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JwtServiceImpl implements JWTService{
//    @Override
//    public String generateToken(User user) {
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", user.getId());
//        map.put("email", user.getEmail());
//        map.put("username", user.getUsername());
//
//        return Jwts.builder()
//                .setClaims(map)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
//                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    @Override
//    public boolean istTokenValid(String token, UserDetails userDetails) {
//
//        return false;
//    }
//
//    private Key getSiginKey(){
//
//        byte[] key = Decoders.BASE64.decode("413F4428472B4B625065536856605978337336763979244226452948404D6351");
//
//        return Keys.hmacShaKeyFor(key);
//
//    }
//}
