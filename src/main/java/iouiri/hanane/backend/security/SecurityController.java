package iouiri.hanane.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder jwtEncoder;

    // Endpoint pour récupérer le profil de l'utilisateur connecté (token JWT valide requis)
    @GetMapping("/profile")
    public Authentication getProfile(Authentication authentication) {
        return authentication;
    }

    // Endpoint POST /auth/login pour s'authentifier et recevoir un JWT
    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        // Authentification
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        Instant now = Instant.now();

        // Récupération des rôles en chaîne "ROLE_USER ROLE_ADMIN" etc.
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // Création du JWT avec claims (sujet, date émission, expiration, scope)
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))  // token valide 30 min
                .subject(username)
                .claim("scope", scope)
                .build();

        // Encodage du JWT avec algorithme HS512
        JwtEncoderParameters params = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),
                claims);

        String token = jwtEncoder.encode(params).getTokenValue();

        return Map.of("access-token", token);
    }
}
