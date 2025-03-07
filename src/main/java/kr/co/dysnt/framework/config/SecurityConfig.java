package kr.co.dysnt.framework.config;

// package kr.co.kisco.kiscotmsmobile.config;
//
// import lombok.RequiredArgsConstructor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
//
// @Configuration
// public class SecurityConfig {
//
// @Bean
// public static PasswordEncoder passwordEncoder() {
// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
// }
//
// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http .sessionManagement()
// .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
// .invalidSessionUrl("/login.do")
// .and()
// .authorizeHttpRequests()
// .antMatchers("/login", "/sessionTest", "/example/**").permitAll()
// .antMatchers("/userAccess").hasRole("USER")
// .antMatchers("/userAccess").hasRole("ADMIN")
// .and()
// .formLogin()
// .loginPage("/login")
// .loginProcessingUrl("/loginProc")
// .defaultSuccessUrl("/userAccess")
// .failureUrl("/accessDenied")
// .usernameParameter("username")
// .passwordParameter("password")
// .and()
// .cors().and().csrf().disable();
// return http.build();
// }
//
//// @Bean
//// public WebSecurityCustomizer webSecurityCustomizer() {
//// return (web) -> web.ignoring().antMatchers("/images/**", "/js/**",
// "/webjars/**", "/**");
//// }
// }
