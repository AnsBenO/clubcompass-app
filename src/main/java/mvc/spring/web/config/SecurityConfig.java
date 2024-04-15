package mvc.spring.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http.csrf(csrf -> csrf.disable())

                                .authorizeHttpRequests(reqs -> reqs
                                                .requestMatchers("/", "/register",
                                                                "/tailwind/**",
                                                                "/register/**", "/css/**",
                                                                "/js/**")
                                                .permitAll().anyRequest().authenticated()

                                )

                                .formLogin(form -> form

                                                .loginPage("/login")

                                                .defaultSuccessUrl("/")

                                                .loginProcessingUrl("/login")

                                                .failureUrl("/login?error=true")

                                                .permitAll()

                                ).logout(

                                                logout -> logout

                                                                .logoutRequestMatcher(
                                                                                new AntPathRequestMatcher("/logout"))
                                                                .permitAll()

                                );

                return http.build();

        }

}
