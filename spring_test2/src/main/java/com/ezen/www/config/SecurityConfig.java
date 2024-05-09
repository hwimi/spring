package com.ezen.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ezen.www.security.CustomAuthUserService;
import com.ezen.www.security.LoginSuccessHandler;
import com.ezen.www.security.LoingFailureHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//비밀번호 암호화 객체 passwordEncoder 빈 생성
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//SuccessHandler객체 빈 생성=>사용자 커스텀 객체
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler(); //아직 생성안함.
	}
	//FailHandler 객체 빈 생성=>사용자 커스텀 객체
	@Bean
	public AuthenticationFailureHandler authFailHandler() {
		return new LoingFailureHandler();
	}
	//UserDetail 객체 빈 생성=>사용자 커스텀 생성
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthUserService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 인증되는 객체로 설정
		auth.userDetailsService(customUserService()).passwordEncoder(bcPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 화면에서 설정되는 권한에 따른 주소 맵핑 설정
		//csrf():공격에 대한 설정 풀기
		http.csrf().disable();
		
		//권한 승인 요청
		//antMatchers:접근을 허용하는 값(경로)
		//permitAll:누구나 접근 가능한 경로
		//authenticated():인증된 사용자만 가능한 경로
		//auth=>hasRole:권한을 확인할때
		//USER, ADMIN, MANAGER
		http.authorizeRequests()
		.antMatchers("/user/list").hasRole("ADMIN").antMatchers("/","/board/register","/board/list","/board/detail","/comment/**","/up/**","/re/**","/user/register","/user/login").permitAll()
		.anyRequest().authenticated();
		
		//커스텀 로그인 페이지 구성
		//Controller에 주소요청 맵핑이 같이 있어야함.(필수)
		http.formLogin().usernameParameter("email")
		.passwordParameter("pwd").loginPage("/user/login").successHandler(authSuccessHandler()).failureHandler(authFailHandler());
		
		//로그아웃 페이지 반드시 method="post"
		http.logout().logoutUrl("/user/logut").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/");
		
	}

	
}
