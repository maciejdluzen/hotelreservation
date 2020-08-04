package pl.maciejdluzen.hotelreservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.maciejdluzen.hotelreservation.services.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final DataSource dataSource;
    private final UserService userService;

    public SecurityConfiguration(DataSource dataSource, UserService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, active FROM users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT u.username, r.name FROM users u JOIN roles r ON r.id = u.role_id WHERE u.username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/hello").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/auth/guest", "/auth/guest/**").hasRole("GUEST")
                .antMatchers("/auth/admin", "/auth/admin/**").hasRole("ADMIN")
                .antMatchers("/auth/reception", "/auth/reception/**").hasRole("RECEPTIONIST")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
            .logout()
                .logoutSuccessUrl("/")
                .and()
            .csrf();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/public_html/**")
                .antMatchers("/h2-console", "/h2-console/**");
    }

}


