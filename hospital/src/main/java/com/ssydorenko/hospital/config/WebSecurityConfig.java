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

        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/doctors/**").authenticated()
                .antMatchers(HttpMethod.POST, "/doctors/**").hasRole(UserRole.CHIEF.toString())
                .antMatchers(HttpMethod.DELETE, "/doctors/**").hasRole(UserRole.CHIEF.toString())

                .antMatchers(HttpMethod.GET, "/patients/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
                .antMatchers(HttpMethod.POST, "/patients/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
                .antMatchers(HttpMethod.DELETE, "/patients/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
                .antMatchers(HttpMethod.PUT, "/patients/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())

                .antMatchers(HttpMethod.GET, "/records/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
                .antMatchers(HttpMethod.POST, "/records/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
                .antMatchers(HttpMethod.DELETE, "/records/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
                .antMatchers(HttpMethod.PUT, "/records/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())

                .antMatchers(HttpMethod.GET, "/requests/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
                .antMatchers(HttpMethod.POST, "/requests/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
                .antMatchers(HttpMethod.DELETE, "/requests/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
                .antMatchers(HttpMethod.PUT, "/requests/**").hasAnyRole(UserRole.CHIEF.toString(), UserRole.DOCTOR.toString())
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
            return new User(username, userEntity.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().toString())));
        }
    }

}
