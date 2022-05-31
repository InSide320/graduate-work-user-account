package com.example.configuration;

import com.example.user.UserPersonalService;
import com.example.user.credentials.role.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserPersonalService userPersonalService;
    private final PasswordEncoder encoder;

    public WebSecurityConfiguration(UserPersonalService userPersonalService, PasswordEncoder encoder) {
        this.userPersonalService = userPersonalService;
        this.encoder = encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPersonalService)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "*").permitAll()
                .antMatchers("/registration", "/activate/*")
                .permitAll()
                .antMatchers("/change-data", "/change-data/entrance")
                .hasAuthority(RoleType.TEACHER.name())
                .antMatchers("/change-data/role-type/*")
                .hasAuthority(RoleType.ADMIN.name())
                .antMatchers("/js/**", "/css/**", "/webjars/**", "/v2/api-docs",
                        "/swagger-ui/#/", "/swagger-resources/**", "/download")
                .hasAuthority(RoleType.SUPER_ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/personal-data", true).permitAll()
                .and()
                .logout().permitAll();

        http.cors().configurationSource(request -> corsConfiguration());

    }

    @Bean
    AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver();
    }

    private CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        return corsConfiguration;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = RoleType.SUPER_ADMIN.name() + " > " + RoleType.ADMIN.name() + " \n "
                + RoleType.ADMIN.name() + " > " + RoleType.TEACHER.name() + "\n"
                + RoleType.TEACHER.name() + " > " + RoleType.STUDENT.name();
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }
}
