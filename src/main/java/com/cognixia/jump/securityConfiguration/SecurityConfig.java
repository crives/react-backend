package com.cognixia.jump.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cognixia.jump.service.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    MyUserDetailService userDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth ) throws Exception {
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
                .antMatchers(HttpMethod.GET, "/api/allUsers").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/update/user").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/add/review").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/add/user").permitAll()
                .antMatchers(HttpMethod.GET, "/api/reviews/restaurant/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/address/{id}").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
				.and().formLogin().permitAll()
				.and().logout().permitAll();
    }

}
