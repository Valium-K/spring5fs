package chap04.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("infoPrinter")
public class MemberInfoPrinter {

    // MemberDao는 setter에서 의존주입을 따로 시킬 것이다.
    private MemberDao memberDao;

    // MemberPrinter는 @Autowired를 통해 자동으로 의존주입을 시킨다.
    // 즉, 설정파일에서 위의 memberDao와는 다르게 의존주입 코드가 없다.
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

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    @Qualifier("printer")
    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }
}
