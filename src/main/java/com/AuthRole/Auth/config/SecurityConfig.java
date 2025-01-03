package com.AuthRole.Auth.config;

import com.AuthRole.Auth.Service.AppUserService;
import com.AuthRole.Auth.model.Auth.user.AppUser;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
    @EnableWebSecurity
@RequiredArgsConstructor
    public class SecurityConfig {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    private final   CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/account").permitAll()
                            .requestMatchers("/account/login").permitAll()
                            .requestMatchers("/account/register").permitAll()
                            .requestMatchers("/account/profile").hasAuthority("ROLE_ADMIN") // Restrict to ROLE_ADMIN
                            .anyRequest().authenticated()
                    )
                    .exceptionHandling(exceptionHandling -> exceptionHandling
                            .authenticationEntryPoint(customAuthenticationEntryPoint) // Custom 401 handler
                            .accessDeniedHandler((request, response, accessDeniedException) -> {
                                customAuthenticationEntryPoint.handleAccessDeniedException(request, response, (AccessDeniedException) accessDeniedException);
                            })
                    )
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .jwt(jwt -> jwt.jwtAuthenticationConverter(jw -> {
                                JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
                                grantedAuthoritiesConverter.setAuthorityPrefix("");
                                grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

                                Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jw);
                                return new JwtAuthenticationToken(jw, authorities, jw.getSubject());
                            }))
                    )
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .build();
        }


        @Bean
        public JwtDecoder jwtDecoder() {
        var secretKey = new SecretKeySpec(jwtSecretKey.getBytes(), "");
           return NimbusJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm (MacAlgorithm.HS256).build();
    }

        @Bean
        public AuthenticationManager authenticationManager (AppUserService appUserService) {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); provider.setUserDetailsService (appUserService);
            provider.setPasswordEncoder (new BCryptPasswordEncoder());
            return new ProviderManager(provider);
        }


        public String createJwtToken(AppUser appUser) {
        Instant now = Instant.now();

        // Map roles to a list of strings
        List<String> roles = appUser.getUserRoles().stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .collect(Collectors.toList());

        // Build JWT claims
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer) // Your issuer name
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600)) // 1 day expiration
                .subject(appUser.getUsername())
                .claim("roles", roles)
                .claim("email", appUser.getEmail())
                .build();

        // Encode the token
        var encoder = new NimbusJwtEncoder(new ImmutableSecret<>(jwtSecretKey.getBytes()));
        var params = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), claims);

        return encoder.encode(params).getTokenValue();
    }


    }
