package by.it_academy.jd2.Mk_JD2_82_21.final_project.configuration;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.controller.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtFilter jwtFilter;

    public SecurityConfig (JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                // GET GetAll Users Only ADMIN
                // POST add new product ADMIN

                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/api/user/login","/api/user/register*").anonymous()
                .antMatchers("/api/**").hasRole("ADMIN")
               // .antMatchers(HttpMethod.GET,"/api/product/**","/api/recipe/**","/api/profile/{id_profile}")
                //.hasAnyRole("ADMIN","USER")
                //.antMatchers(HttpMethod.GET,"/api/product/").hasRole("USER,ADMIN")
                //.antMatchers("/api/user/**").anonymous()
                //.antMatchers("/api/product/**").hasRole("USER")

                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
