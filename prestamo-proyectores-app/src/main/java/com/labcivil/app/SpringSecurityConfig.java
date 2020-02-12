package com.labcivil.app;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.labcivil.app.auth.handler.LoginSuccessHandler;
import com.labcivil.app.models.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	 @Autowired
	 private LoginSuccessHandler successHandler;

	//UTILIZANDO JDBC
//	@Autowired
//	private DataSource dataSource;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar**", "/graficas", "/lab-civil",
				"/user/form2", "/cargar-proyectoresg", "/cargar-materiasg", "/cargar-estudiantesg", "/cargar-profesoresg",
				 "/cargar-proyectoresg1", "/cargar-materiasg1", "/cargar-estudiantesg1", "/cargar-profesoresg1","/cargar-estatusproyg",
				"/sendFecha", "/listadoFecha","/sendHora", "/listadoHora", "/sendFechaGrafica","/graficaHoursUsed").permitAll()
				 .antMatchers("/ver/**").hasAnyRole("USER")
				 .antMatchers("/uploads/**").hasAnyRole("USER")
				 .antMatchers("/form/**").hasAnyRole("ADMIN")
				 .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
				 .antMatchers("/registro/**").hasAnyRole("ADMIN")
				 .antMatchers("/materia/**").hasAnyRole("ADMIN")
				 .antMatchers("/profesor/**").hasAnyRole("ADMIN")
				 .antMatchers("/estudiante/**").hasAnyRole("USER")
//				.anyRequest().authenticated()
				.anyRequest().authenticated().and().formLogin()
//				.loginPage("/login").permitAll().and().logout().permitAll();				
				.successHandler(successHandler)
				.loginPage("/login").permitAll().and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/error_403");
	}
	

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		
		//UTILIZANDO JDBC
//		build.jdbcAuthentication()
//		.dataSource(dataSource)
//		.passwordEncoder(passwordEncoder)
//		.usersByUsernameQuery("select username, password, enabled from users where username=?")
//		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?");

		//UTILIZANDO JPA
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);

	}
}
