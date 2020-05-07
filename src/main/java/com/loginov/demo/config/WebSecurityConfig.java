package com.loginov.demo.config;

import com.loginov.demo.dao.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDAO userDAO;
    private EncoderConfig encoderConfig;

    public WebSecurityConfig(UserDAO userDAO, EncoderConfig encoderConfig) {
        this.userDAO = userDAO;
        this.encoderConfig = encoderConfig;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/auth").not().fullyAuthenticated()
                .antMatchers("/api").not().fullyAuthenticated()
                .antMatchers("/swagger*").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/person").hasRole("USER")
                .antMatchers("/diagnosis").hasRole("USER")
                .antMatchers("/ward").hasRole("USER")
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated();
    }


    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDAO).passwordEncoder(encoderConfig.bCryptPasswordEncoder());
    }
}
