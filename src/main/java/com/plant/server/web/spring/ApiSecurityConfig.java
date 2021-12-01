package com.plant.server.web.spring;

import com.plant.server.web.controller.api.ApiURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthEntryPoint restAuthEntryPoint;

    @Autowired
    private AuthRequestFilter jwtRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher(ApiURL.BASE + "/**")
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, ApiURL.BASE + "/**").permitAll()

                .antMatchers(ApiURL.BASE + ApiURL.PUBLIC + "/**").permitAll()

                .antMatchers(ApiURL.BASE + ApiURL.AUTH + "/**").permitAll()


                .antMatchers(ApiURL.BASE + "/**").hasAuthority(UserDetailsService.AUTHORITY_ADMIN)

                //.antMatchers(ApiURL.BASE+ApiURL.ADMIN+"/**").hasAuthority(UserDetailsService.AUTHORITY_ADMIN)

                //.antMatchers(ApiURL.BASE + "/**").hasAnyAuthority(UserDetailsService.AUTHORITY_ADMIN)
                .and()
                .exceptionHandling().authenticationEntryPoint(this.restAuthEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors();
        http.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}