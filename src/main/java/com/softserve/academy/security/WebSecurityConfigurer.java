package com.softserve.academy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity//(debug = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity // prePostEnabled = true by default is true.
public class WebSecurityConfigurer {

    private final UsernamePasswordAuthenticationFilter authenticationFilter;

    @Autowired
    public WebSecurityConfigurer(@Lazy UsernamePasswordAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    //protected SecurityWebFilterChain configure(HttpSecurity http) throws Exception {
        /* // for spring-boot 2.x
        http.httpBasic().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login-form").permitAll()
                .antMatchers("/users/create").permitAll()
                .anyRequest().authenticated();

        http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(
                (request, response, authException) -> response.sendRedirect(request.getContextPath() + "/login-form"));
        */
        // // for spring-boot 3.1.0
//        http.httpBasic()
//                .disable().csrf()
//                .disable().cors()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests()
                //.authorizeRequests()
                //.requestMatchers(HttpMethod.GET, "/login-form").permitAll()
                //.requestMatchers("/users/create").permitAll();
                //.anyRequest().authenticated();
//                .requestMatchers("/**").permitAll();
                //.anyRequest().permitAll();

        http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(
                (request, response, authException) -> response.sendRedirect(request.getContextPath() + "/login-form"));
        return http.build();
        //
        /* // for spring-boot 3.1.0
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> {
                    httpSecuritySessionManagementConfigurer
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                });
//        http
//                .securityMatcher("/", "/login-form", "/users/create")
//                .authorizeHttpRequests(requests -> requests.anyRequest().permitAll());
//                .securityMatcher("/users/create")
//                .authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                //.securityMatcher(PathRequest.toStaticResources().toString())
                //.securityMatcher(EndpointRequest.toAnyEndpoint().toString())
                //.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(auth -> auth.authenticationEntryPoint(
                (request, response, authException) -> response.sendRedirect(request.getContextPath() + "/login-form")));
        return http.build();
        */
    }

    @Bean("bCrypt")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
