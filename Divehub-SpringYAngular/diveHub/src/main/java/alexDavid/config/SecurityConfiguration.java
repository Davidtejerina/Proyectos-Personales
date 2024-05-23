package alexDavid.config;


import alexDavid.Auth.JwtAuthenticationFilter;
import alexDavid.repository.UserDetailsRepository;
import alexDavid.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserDetailsRepository userDetailsRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        RequestMatcher h2ConsoleMatcher = new AntPathRequestMatcher("/h2-console/**");
        http
                .csrf(csrf -> csrf.disable())
                .cors()  // Aunque de deprecated, lo sigue cogiendo y permite la comunicacion cruzada entre distintos dominios,
                            // Cosa que normalmente esta prohibido ya que dos dominios no pueden comunicarse pero al tener /api/auth y /api/categories
                            // Queremos que se comuniquen.
                .and()
                .headers().frameOptions().disable() // Deshabilitar frameOptions para permitir la consola H2
                .and()
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(mvc.pattern("/divehub/auth/login")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/auth/signup")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/users/existsNickname/{nickname}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/users/existsEmail/{email}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/messages/addMessage")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/products/all")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/products/getProductById/{id}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/products/getProductByName/{name}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/products/tag/{tag}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/products/category/{category}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/items/all")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/items/getItem/{id}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/items/tag/{tag}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/items/name/{name}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/items/price/{orderedBy}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/activities/all")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/activities/getActivityById/{id}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/activities/getActivityByName/{name}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/activities/tag/{tag}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/activities/category/{category}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/activities/available")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/activities/available_spaces/{id}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/activities/remaining-time/{id}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/assessments/allByProduct/{product_id}")).permitAll()
                        .requestMatchers(mvc.pattern("/divehub/assessments/countTotalByProduct/{product_id}")).permitAll()
                        .requestMatchers(h2ConsoleMatcher).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(customizer -> customizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userDetailsRepository, passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
}