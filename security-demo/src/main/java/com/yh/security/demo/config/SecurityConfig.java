package com.yh.security.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * UserDetailsService是security框架提供的服务
     */
    @Autowired
    private UserDetailsService myUserDetailService;

    @Autowired
    private DataSource dataSource;

    /**
     * 配置用户自动登录的PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // table已经创建过了
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置登录url, 不需要认证的url
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置auto login

        // 配置logout
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/signin.html").permitAll();
        // 设置没有访问权限
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        http.formLogin()
                .loginPage("/signin.html")
                .loginProcessingUrl("/user/signin") //登录路径
                .defaultSuccessUrl("/success.html") //认证成功后跳转路径
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/user/signin", "/user/hello") // 设置哪些路径不需要认证
                .permitAll()
                .anyRequest().authenticated()
                .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(24 * 3600) // 设置自动登录
                .userDetailsService(myUserDetailService) //设置使用这个userDetailService操作DB
                ;
//                .and().csrf().disable(); //测试关闭csrf校验
    }
}
