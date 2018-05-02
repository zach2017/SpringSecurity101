package zachdemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;

@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() throws Exception {
	// ensure the passwords are encoded properly
	UserBuilder users = User.withDefaultPasswordEncoder();
	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); 
	
	manager.createUser(users.username("user").password("password").roles("USER").build()); 
	manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build()); 
	return manager;
	}
	
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
	
		protected void configure(HttpSecurity http) throws Exception { 
		http.antMatcher("/private/**")
		.authorizeRequests()
		        .anyRequest().hasRole("ADMIN")
		        .and()
		.httpBasic();
	   }
	}

	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception { 
			
			http	.authorizeRequests()
		        .anyRequest().authenticated()
		        .and()
		.formLogin();
	}
	} 
	
	/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth
            .inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("user").password("password").roles("USER");
    }
    
    @Configuration 
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/private/**").denyAll();
                  // .anyRequest().hasRole("USER");
        }
    } 
    */

    //    @Autowired
	//public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    //        request.getRequestDispatcher("/spring/denied").forward(request, response);
      //  }
   // 

}