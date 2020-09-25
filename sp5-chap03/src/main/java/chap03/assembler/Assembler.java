package chap03.assembler;

import chap03.spring.ChangePasswordService;
import chap03.spring.MemberDao;
import chap03.spring.MemberRegisterService;

// 스프링의 @Configuration 을 이용하지 않고 직접 POJO로 코딩할 경우의 설정파일
public class Assembler {
    private MemberDao memberDao;
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc;

    public Assembler() {
        // DI 시에 아래 new MemberDao();를 new CachedMemberDao();로 바꿀 수있다.
        memberDao = new MemberDao();
        regSvc = new MemberRegisterService(memberDao);
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }
    public MemberRegisterService getMemberRegisterService() {
        return regSvc;
    }
    public ChangePasswordService getChangePasswordService() {
        return pwdSvc;
    }
}
