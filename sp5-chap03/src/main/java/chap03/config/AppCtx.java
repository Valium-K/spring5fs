package chap03.config;

import chap03.spring.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import chap03.spring.*;

// 해당 설정파일은 AppCtx1 + AppCtx2와 동일하다.
@Configuration
public class AppCtx {

    // 생성된 MemberDao 객체는 스프링 컨테이너가 관리하는 싱글톤 객체이다.
    // 즉, 아래 memberRegisterService()와 changePasswordService()가 사용하는 MemberDao객체는 같은 객체이다.
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        ChangePasswordService passwordService = new ChangePasswordService();
        passwordService.setMemberDao(memberDao());

        return passwordService;
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    // 생성자 방식 주입
    @Bean
    public MemberListPrinter memberListPrinter() {
        return new MemberListPrinter(memberDao(), memberPrinter());
    }

    // setter 방식 주입
    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao());

        // MemberPrinter 는 MemberInfoPrinter 클래서에서
        // @Autowired 를 통해 자동 의존주입이되어 아래 코드를 작성 할 필요가 없다.
        //infoPrinter.setPrinter(memberPrinter());

        return infoPrinter;
    }



    // 기본 데이터 타입 값 설정
    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(1);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}
