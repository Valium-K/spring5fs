package chap03.config;

import chap03.spring.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import chap03.spring.*;

@Configuration
public class AppCtx2 {

    // intellij 버그로 빨간 줄 나올 수 있음.
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberPrinter memberPrinter;

    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService(memberDao);
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        ChangePasswordService passwordService = new ChangePasswordService();
        passwordService.setMemberDao(memberDao);

        return passwordService;
    }


    // 생성자 방식 주입
    @Bean
    public MemberListPrinter memberListPrinter() {
        return new MemberListPrinter(memberDao, memberPrinter);
    }

    // setter 방식 주입
    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao);
        
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
