package chap03.spring;

import org.springframework.beans.factory.annotation.Autowired;
import chap03.spring.*;
public class MemberInfoPrinter {

    // MemberDao는 setter에서 의존주입을 따로 시킬 것이다.
    private MemberDao memberDao;

    // MemberPrinter는 @Autowired를 통해 자동으로 의존주입을 시킨다.
    @Autowired
    private MemberPrinter printer;

    public void printMemberInfo(String email) {
        Member member = memberDao.selectByEmail(email);
        if(member == null) {
            System.out.println("There is no such data");
            return;
        }

        printer.print(member);
        System.out.println();
    }


    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }
}
