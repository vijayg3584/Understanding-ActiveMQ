package au.com.unico.codingtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class RestSecurityConfig  extends WebSecurityConfigurerAdapter
{
	
	private static final String REALM = "UNICO_SECURITY_REALM";

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception
	{
		builder.inMemoryAuthentication().withUser("unico-user1").password("a1a2a3a4").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable().authorizeRequests()
		.antMatchers("/restapi/**").hasAnyRole("USER").and()
		.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	private AuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new BasicAuthenticationEntryPoint(){
			@Override
			public void afterPropertiesSet() throws Exception {
				setRealmName(REALM);
				super.afterPropertiesSet();
			}
		};
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/restapi/**");
	}
}
