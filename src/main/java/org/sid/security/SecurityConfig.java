package org.sid.security;



import javax.sql.DataSource;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;




@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource; //utilisation du même data source défini dans app.propr

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("USER","ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("{noop}4321").roles("USER");
		
		/*auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?")
		.authoritiesByUsernameQuery("select username as principal,role as role from users_role where username =?")
		.passwordEncoder();*/
		
	}
	
   /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("{noop}1234")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
		http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
	}
	
		
}
