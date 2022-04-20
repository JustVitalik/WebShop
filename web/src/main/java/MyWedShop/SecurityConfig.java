package MyWedShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomDetailsService customDetailsService;

    @Autowired
    public SecurityConfig(CustomDetailsService customDetailsService) {
        this.customDetailsService = customDetailsService;

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/register","/product/details/**","/product/display/**","/product/show").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/register").permitAll()
                .and()
                .logout().permitAll();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customDetailsService);
//                .passwordEncoder(passwordEncoder());
    }
@Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder(8);
    }
}
