package com.iljaust.theapp.config;

import com.iljaust.theapp.security.jwt.JwtConfigurer;
import com.iljaust.theapp.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String LOGIN_ENDPOINT = "/api/v1/auth/*";
    private static final String FILE_ENDPOINT = "/api/v1/files/*";
    private static final String STORAGE_ENDPOINT = "/api/v1/file/*";
    private static final String EVENT_ENDPOINT = "/api/v1/events/*";
    private static final String USER_ENDPOINT = "/api/v1/users/*";



    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(STORAGE_ENDPOINT).hasAnyRole("ADMIN", "MODER", "USER")
                .antMatchers(HttpMethod.GET,  FILE_ENDPOINT).hasAnyRole("ADMIN", "MODER", "USER")
                .antMatchers(HttpMethod.POST,  FILE_ENDPOINT).hasAnyRole("ADMIN", "MODER")
                .antMatchers(HttpMethod.PUT,  FILE_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,  FILE_ENDPOINT).hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, USER_ENDPOINT).hasAnyRole("ADMIN", "MODER")
                .antMatchers(HttpMethod.POST, USER_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, USER_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, USER_ENDPOINT).hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, EVENT_ENDPOINT).hasAnyRole("ADMIN", "MODER", "USER")
                .antMatchers(HttpMethod.POST, EVENT_ENDPOINT).hasAnyRole("ADMIN", "MODER")
                .antMatchers(HttpMethod.PUT, EVENT_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, EVENT_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
