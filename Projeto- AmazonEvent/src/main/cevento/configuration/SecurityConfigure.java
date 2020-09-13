package com.spring.cevento.configuration;

import com.spring.cevento.configuration.UserDetailsServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServicelmpl udl;

    private static final String[] AUTH_LIST={
            "/cadastro"

    };

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
        .antMatchers(AUTH_LIST).permitAll()
                .antMatchers(HttpMethod.GET, "/newevento").hasAnyRole ("ADMIN","MODERA")
                .antMatchers(HttpMethod.POST, "/newevento").hasAnyRole ("ADMIN","MODERA")
                .antMatchers(HttpMethod.GET, "/meuseventos").hasAnyRole ("ADMIN","MODERA")
                .antMatchers(HttpMethod.GET,"/meuseventos/{id}").hasAnyRole ("ADMIN","MODERA")
                .anyRequest().authenticated()
                .and()
               .csrf().disable().formLogin().loginPage("/login")
               .failureUrl("/login?error=true")
                .defaultSuccessUrl("/",true)
                .usernameParameter("login")
                .passwordParameter("senha")
                .permitAll()
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
        .and().exceptionHandling().accessDeniedPage("/403");
    }

   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{


        auth.userDetailsService(udl)
               .passwordEncoder(new BCryptPasswordEncoder());

   }
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }




    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/images/**","/bootstrap/**","/templates/**");
    }
}
