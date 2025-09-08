package com.shop.in.BeanConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfig {

    @Autowired
    private UserDetailsService userDetails;

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        //                        .ignoringRequestMatchers("/saveMsg")
        //                        .ignoringRequestMatchers("/updateProfile"))
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/displayProfile").authenticated()
                        .requestMatchers("/updateProfile").authenticated()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/holidays").permitAll()
                        .requestMatchers("/data-api/**").hasAnyRole("ADMIN")
                        .requestMatchers("/student/**").hasAnyRole("USER")
                        .requestMatchers("/assets/**").permitAll() //permit all css ,js, images and all other static files
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/public/register").permitAll()
                        .requestMatchers("/public/createUser").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/displayMessages/**").hasAnyRole("ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/closeMsg").hasAnyRole("ADMIN")
                        .requestMatchers("/actuator/**").hasRole("ADMIN")
                        .requestMatchers("/contact" ,"/saveMsg").authenticated()
                        .requestMatchers("/deleteMsg/**").permitAll()
        )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard",true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                .oauth2Login(oauth -> oauth.loginPage("/login"))

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID"))

                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder encoder(){
       return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder());
        provider.setUserDetailsService(userDetails);
        return provider;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        ClientRegistration github = githubClientRegistration();
        return new InMemoryClientRegistrationRepository(github);
    }

    @Bean
    public ClientRegistration githubClientRegistration(){
        return CommonOAuth2Provider
                .GITHUB.getBuilder("github")
                .clientId("Ov23liDukhYpEEicNSqI")
                .clientSecret("8acc5a19236017b22cf790b70e53eec05da9e1aa")
                .build();
    }


}
