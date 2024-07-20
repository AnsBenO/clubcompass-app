package org.clubcompass.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final CustomUserDetailsService customUserDetailsService;

        @Bean
        static PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();

        }

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http.csrf(Customizer.withDefaults());
                // Define which requests are allowed without authentication
                http.authorizeHttpRequests(request -> request
                                .requestMatchers(
                                                "/",
                                                "/register",
                                                "/tailwind/**",
                                                "/register/**",
                                                "/css/**",
                                                "/js/**",
                                                "/assets/**")
                                .permitAll()

                                // All other requests need to be authenticated
                                .anyRequest().authenticated());

                // Configure form login
                http.formLogin(form -> form
                                // Login page URL
                                .loginPage("/login")

                                // After successful login, user should be redirected to home page
                                .defaultSuccessUrl("/")

                                // Login processing URL
                                .loginProcessingUrl("/login")

                                // URL to redirect user to in case they fail to login
                                .failureUrl("/login?error=true")

                                // Allow anyone to access these URLs

                                .permitAll());

                // Configure logout
                http.logout(logout -> logout

                                // URL for logout requests
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                // Allow anyone to access these URLs
                                .permitAll());
                return http.build();

        }

        /**
         * Configures the authentication manager builder.
         *
         * @param builder the authentication manager builder
         * @throws Exception if an error occurs during configuration
         */
        @Autowired
        public void configure(AuthenticationManagerBuilder builder) throws Exception {
                builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        }

}
