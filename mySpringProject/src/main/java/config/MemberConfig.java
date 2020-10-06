package config;

import controller.MainController;
import controller.MemberController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import spring.member.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MemberConfig {

    @Bean
    public MemberDB memberDB() {
        MemberDB memberDB =  new MemberDB();
        memberDB.setMemberList(new ArrayList<>());

        return memberDB;
    }
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }
    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService();
    }
    @Bean
    public MemberController memberController() {
        return new MemberController();
    }

//    @Bean
//    public RegisterRequest registerRequest() {
//        return new RegisterRequest();
//    }
}
