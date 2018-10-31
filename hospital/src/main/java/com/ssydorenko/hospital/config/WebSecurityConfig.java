package com.ssydorenko.hospital.config;

import com.ssydorenko.hospital.db.repository.UserEntityRepository;
import com.ssydorenko.hospital.domain.entity.UserEntity;
import com.ssydorenko.hospital.domain.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/doctors/**").authenticated()
            .antMatchers(HttpMethod.POST, "/doctors/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.DELETE, "/doctors/**").hasRole(UserRole.CHIEF.toString())

            .antMatchers(HttpMethod.GET, "/patients/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.GET, "/patients/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.POST, "/patients/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.POST, "/patients/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.DELETE, "/patients/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.DELETE, "/patients/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.PUT, "/patients/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.PUT, "/patients/**").hasRole(UserRole.DOCTOR.toString())

            .antMatchers(HttpMethod.GET, "/records/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.GET, "/records/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.POST, "/records/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.POST, "/records/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.DELETE, "/records/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.DELETE, "/records/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.PUT, "/records/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.PUT, "/records/**").hasRole(UserRole.CHIEF.toString())

            .antMatchers(HttpMethod.GET, "/requests/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.GET, "/requests/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.POST, "/requests/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.POST, "/requests/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.DELETE, "/requests/**").hasRole(UserRole.CHIEF.toString())
            .antMatchers(HttpMethod.DELETE, "/requests/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.PUT, "/requests/**").hasRole(UserRole.DOCTOR.toString())
            .antMatchers(HttpMethod.PUT, "/requests/**").hasRole(UserRole.CHIEF.toString())
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(customBasicAuthenticationEntryPoint);
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(new AuthUserService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        auth.authenticationProvider(daoAuthenticationProvider);
    }


    private class AuthUserService implements UserDetailsService {

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserEntity userEntity = userEntityRepository.getOne(username);
            if (userEntity == null) {
                throw new IllegalArgumentException("User " + username + " does not exist");
            }

            return new User(username, userEntity.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().toString())));
        }
    }

}
