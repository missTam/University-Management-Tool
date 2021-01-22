package de.academy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/* SpringSecurity defines login logic; configures our users
 and also restricts access to the app based on roles */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DashboardRedirectAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider(passwordEncoder, userService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/list/students").permitAll()
                .antMatchers("/list/professors").permitAll()
                .antMatchers("/list/lectures").permitAll()
                .antMatchers("/registration-form").permitAll()
                .antMatchers("/student-dashboard/**").hasRole("STUDENT")
                .antMatchers("/professor-dashboard/**").hasRole("PROFESSOR")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate-user")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout().permitAll();
    }

    // We assign the UserService and PasswordEncoder to the DaoAuthenticationProvider
    // custom user Service can be passed in because it extends UserDetailsService & overrides required method
    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder); //set the password encoder - bcrypt
        return auth;
    }

    // Ignore any request that starts with "/resources/"; bypass spring security for static resources
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**");
    }
}