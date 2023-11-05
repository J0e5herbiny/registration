package com.joe.project.security;

import com.joe.project.service.UserSecurityService;
import com.joe.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private CustomAuthSuccessHandler customAuthSuccessHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider =
                new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService( userSecurityService );
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/managers/**").hasRole("MANAGER")
                .antMatchers("/systems/**").hasRole("ADMIN")

                .and()

                .formLogin()
                .loginPage("/loginAuthPage")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(customAuthSuccessHandler).permitAll()

                .and()

                .logout().permitAll()

                .and()

                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}
