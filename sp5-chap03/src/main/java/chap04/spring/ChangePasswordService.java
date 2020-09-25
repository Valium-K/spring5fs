package chap04.spring;

import org.springframework.beans.factory.annotation.Autowired;
import chap04.spring.exception.MemberNotFoundException;

public class ChangePasswordService {

    // 설정 클래스에서 의존주입을 하지 않아도 됨
    // 스프링이 자동으로 MemberDao 타입을 찾아 의존주입을 한다.
    @Autowired
    private MemberDao memberDao;

    public void changePassword(String email, String oldPassword, String newPassword) {
        Member member = memberDao.selectByEmail(email);

        if(member == null) {
            throw new MemberNotFoundException();
        }

        member.changePassword(oldPassword, newPassword);

        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
