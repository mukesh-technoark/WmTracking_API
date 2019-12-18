/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wmtrucking.exceptions.InvalidTokenException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @author Mukesh
 */
@Component
public class MaJWT {

    Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_KEY.toString());

//    public String generate(Long id,
//            String username,
//            String host,
//            String devicetoken) throws JWTCreationException {
//        return JWT.create()
//                .withJWTId(String.valueOf(id))
//                .withIssuer("786")
//                .withClaim("username", username)
//                .withClaim("host", host)
//                .sign(algorithm);
//    }
      public String generate(Long id) throws JWTCreationException {
        return JWT.create()
                .withJWTId(String.valueOf(id))
                .withIssuer("786")              
                .sign(algorithm);
    }

    public String generateWithExpires(Long id, int expTimeInSec) throws JWTCreationException {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, expTimeInSec);
        return JWT.create()
                .withExpiresAt(c.getTime())
                .withJWTId(String.valueOf(id))
                .withIssuer("786")
                .withClaim("username", "")
                .withClaim("host", "")
                .sign(algorithm);
    }

    public Boolean verify(String token) throws JWTVerificationException, InvalidTokenException {
        try {
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            throw new InvalidTokenException("Error", Arrays.asList("Your sessoin is expired."));
        }
    }

    public String getId(String jwt, String token, String devicetoken) throws InvalidTokenException {
        try {
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();
            return verifier.verify(token).getId();

        } catch (Exception e) {
            throw new InvalidTokenException("Error", Arrays.asList("Your sessoin is expired."));
        }
    }

}
