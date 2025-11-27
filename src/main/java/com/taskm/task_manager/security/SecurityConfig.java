package com.taskm.task_manager.security;


import com.taskm.task_manager.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final CustomUserDetailsService userDetailsService;
//
//
//    public SecurityConfig(CustomUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////             /*  .csrf(csrf -> csrf.disable()) // disabilita CSRF per test con Postman
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()
////                        .requestMatchers("/api/public/**").permitAll()
////                        .anyRequest().authenticated() // tutto il resto richiede autenticazione
////                )
////                .formLogin(form -> form
////                        .loginPage("/login")
////                        .defaultSuccessUrl("/home", true)
////                        .permitAll()
////                )
////                .logout(logout -> logout
////                        .logoutUrl("/logout")
////                        .logoutSuccessUrl("/login?logout")
////                        .permitAll()
////                );*/
////                .cors(cors -> {})
////                .csrf(csrf -> csrf.disable())
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
////                .authorizeHttpRequests(auth -> auth
////                        //.requestMatchers("/api/public/**", "/api/login").permitAll()
////                        .requestMatchers("/api/public/**","/api/login/**").permitAll()//"/api/progetti/**","/api/utenti","/api/anagrafica","/api/utenti/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .formLogin(form -> form.disable());
////                //.formLogin(Customizer.withDefaults());
////
////        return http.build();
////    }
//
//
//
//
//
//    // AuthenticationManager che usa il provider corretto
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//////
////        return authenticationConfiguration.getAuthenticationManager();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        // per password in chiaro
////        return NoOpPasswordEncoder.getInstance();
////    }
////
////    // CORS per Angular
////    @Bean
////    public WebMvcConfigurer corsConfigurer() {
////        return new WebMvcConfigurer() {
////            @Override
////            public void addCorsMappings(CorsRegistry registry) {
////                registry.addMapping("/**")
////                        .allowedOrigins("http://localhost:4200")
////                        .allowedMethods("*")
////                        .allowCredentials(true);
////            }
////        };
////    }
//
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .cors(cors -> {})
//            .csrf(csrf -> csrf.disable())
//            .sessionManagement(session ->
//                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/api/login").permitAll()
//                    .requestMatchers("/api/public/**").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .authenticationProvider(authProvider())   // <---- MANCAVA!!!
//            .formLogin(form -> form.disable());
//
//    return http.build();
//}
//
//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            HttpSecurity http,
//            DaoAuthenticationProvider authProvider
//    ) throws Exception {
//
//        AuthenticationManagerBuilder authBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//
//        authBuilder.authenticationProvider(authProvider);
//
//        return authBuilder.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // ok per test
//    }
//
//}
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(CustomUserDetailsService uds, JwtFilter jwtFilter) {
        this.userDetailsService = uds;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // usare BCrypt in produzione
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form.disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
