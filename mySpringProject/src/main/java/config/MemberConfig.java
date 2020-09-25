package config;

import controller.MemberController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import spring.member.Member;
import spring.member.MemberDB;
import spring.member.MemberDao;
import spring.member.MemberRegisterService;

@Configuration
public class MemberConfig {

    @Bean
    public MemberDB memberDB() {
        return new MemberDB();
    }
    @Bean
    public MemberDao memberDao(MemberDB memberDB) {
        return new MemberDao(memberDB);
    }
    @Bean
    public MemberRegisterService memberRegisterService(MemberDao memberDao) {
        return new MemberRegisterService(memberDao);
    }
    @Bean
    public MemberController memberController() {
        return new MemberController();
    }
    
}
