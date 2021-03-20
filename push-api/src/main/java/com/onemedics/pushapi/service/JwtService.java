package com.onemedics.pushapi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Service
@AllArgsConstructor
public class JwtService {
    public Map<String, Object> getJwtInfo() throws IOException, GeneralSecurityException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        Resource resource = new ClassPathResource("publicKey.pem");
        String p = IOUtils.toString(resource.getInputStream());
        PublicKey publicKey = getPublicKeyFromString(p);
        try {
            claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwt.split(" ")[1]);
        } catch (Exception e) {
            log.error("", e);
            throw new UserPrincipalNotFoundException("");
        }
        return (Map<String, Object>) claims.getBody();
    }

    public static RSAPublicKey getPublicKeyFromString(String key) throws GeneralSecurityException {
        String publicKeyPEM = key;
        publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----\n", "");
        publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
        byte[] encoded = Base64.decodeBase64(publicKeyPEM);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
        return pubKey;
    }
}
