package sem3.its.ReReddit.business.impl.accesstoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sem3.its.ReReddit.business.exception.InvalidAccessTokenException;
import sem3.its.ReReddit.business.services.AccessTokenDecoder;
import sem3.its.ReReddit.business.services.AccessTokenEncoder;
import sem3.its.ReReddit.domain.AccessToken;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:application.properties")
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    @Autowired
    private Environment env;

    private final Key key;

    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken token){
        Map<String, Object> claimsMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(token.getRoles())){
            claimsMap.put("roles", token.getRoles());
        }
        if(token.getUserId() != null){
            claimsMap.put("userId", token.getUserId());
        }

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(token.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    @Override
    public AccessToken decode(String encodedToken){
        try{
            Jwt jwt = Jwts.parserBuilder().setSigningKey(key).build().parse(encodedToken);
            Claims claims = (Claims) jwt.getBody();

            List<String> roles = claims.get("roles", List.class);

            return AccessToken.builder()
                    .subject(claims.getSubject())
                    .roles(roles)
                    .userId(claims.get("userId", Long.class))
                    .build();
        } catch(JwtException exception){
            throw new InvalidAccessTokenException(exception.getMessage());
        }
    }
}
