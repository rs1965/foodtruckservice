package com.radient.ftsapp.service;

import com.radient.ftsapp.utils.ResponseObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {

    private final String developerId = "8de1a14f-3030-43e8-b5e4-085e5bd755e6";
    private final String keyId = "2dcd171e-3671-4b24-b75f-3dbacf021f15";
    private final String signingSecret = "3ZuwOzWv3uMw6A7bI7d2a1uYh-ZYB7UeElgOAxLC0CU";


    public ResponseObject<String> generateToken() {
        // Create the JWT claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", developerId);
        claims.put("kid", keyId);
        claims.put("aud", "doordash");

        // Set token expiry to 5 minutes
        claims.put("exp", ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(5).toEpochSecond());
        claims.put("iat", ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());

        // Decode the signing secret and create the signing key
        byte[] keyBytes = Decoders.BASE64URL.decode(signingSecret);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        // Build and return the JWT
        String value =  Jwts.builder()
                .setHeaderParam("dd-ver", "DD-JWT-V1")
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(key)
                .compact();

        return new ResponseObject<>(true, "success", value);
    }
}
