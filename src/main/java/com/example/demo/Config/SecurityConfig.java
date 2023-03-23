package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.LoginService;

/**
 * SpringSecurityの操作を行うConfigクラス.
 * 
 * @author kenji.suzuki
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{

	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/html/**" ,"/css/**", "/js/**", "/fonts/**");
	}

	/**
	 * @param http
	 * @return ログイン成功時は商品一覧、ログイン失敗時はログイン画面に遷移.
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// ログインしていなくても遷移できる場所
		http.authorizeHttpRequests(
				(requests) -> requests.requestMatchers(
				"/items/showItemList","/items/search","/items/next","/items/selectPage","/items/childCategory",
				"/items/grandCategory","/register/in","/register/insert","/register","/login","/login/loginUser",
				"/add/addList","/add/childCategory","/add/grandCategory","/edit/childCategory","/edit/grandCategory",
				"/add/insertAdd","/edit/showEdit","/edit/update", "/itemDetail")
				.permitAll().anyRequest().authenticated()).csrf().disable();

//		http.csrf().disable(); // 無効にする.ajax使用時にエラーが出るのを回避するため.

//		http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
		
		http.formLogin() // ログイン時の設定
			.loginPage("/items/showItemList") // ログイン画面
			.loginProcessingUrl("/login/loginUser") // ログイン情報の送信先URL
			.failureUrl("/login/error=true") // ログイン失敗後のパス
			.defaultSuccessUrl("/items/showItemList", true) // ログイン成功後リダイレクト先
			.usernameParameter("username") // 認証時に使用するユーザ名のリクエストパラメータ名
			.passwordParameter("password");// 認証時に使用するパスワードのリクエストパラメータ名

		http.csrf().disable(); // 無効にする.ajax使用時にエラーが出るのを回避するため.
		
		http.logout() // ログアウトの設定
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //ログアウト後の遷移先
			.logoutSuccessUrl("/login/loginUser") // ログアウト後に遷移させるパス(ここではログイン画面を設定)
			.deleteCookies("JSESSIONID") // ログアウト後Cookieに保存されているsessionIDを削除
			.invalidateHttpSession(true); // ログアウト後sessionを削除

		return http.build();
	}
	
//	// パスワードハッシュ化
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
