package com.cognixia.jump.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Profile("test")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("myUserDetailService")
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth ) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password("123")
//                .roles("USER")
//                .and()
//                .withUser("admin1")
//                .password("123")
//                .roles("ADMIN");
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/userApi/allUsers").hasRole("USER")
                .antMatchers( HttpMethod.PUT, "/userApi/update/user").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/userApi/add/user").hasRole("USER")
                .antMatchers("/").hasAnyRole("ADMIN", "USER")
                .antMatchers("/userApi/users/{id}").permitAll()
                .and().formLogin()
                .and().httpBasic();
    }

}
