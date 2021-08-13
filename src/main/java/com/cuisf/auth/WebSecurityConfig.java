package com.cuisf.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中配置用户名密码
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("user"))
                .roles("user")

                .and()

                .withUser("mlt")
                .password(passwordEncoder.encode("mlt"))
                .roles("admin")

                .and()
                .passwordEncoder(passwordEncoder);//配置BCrypt加密
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      /*  //开启HttpBasic 认证
        http.httpBasic()
                //连接符号
                .and()
                //认证所有的请求
                .authorizeRequests()
                //任何请求必须认证成功
                .anyRequest()

                .authenticated();*/

        //任何请求都会被认证，所以的请求都会被拦截，包括登录 html
      http.authorizeRequests()
              //放行不需要拦截的请求
              .antMatchers("/login","/login.html").permitAll()

              .antMatchers("/users","/roles")
              .hasAnyAuthority("ROLE_user","ROLE_admin")

              .antMatchers("/menus","/others")
              .hasAnyRole("admin")


              .anyRequest()
              .authenticated()


              .and()
              //设置登录界面
              .formLogin().loginPage("/login.html")
              //设置form表单的登录控制器 默认是login 设置表单的action
        .loginProcessingUrl("/login")
              //默认的账户密码
              .usernameParameter("username").passwordParameter("password")
              //登录成功后默认跳转路径
                .defaultSuccessUrl("/home");

      //关闭跨域
        http.csrf().disable();


    }
}
