package chap04.spring;

public class MemberPrinter {
    public void print(Member member) {
        System.out.printf(
                "Member info: Id: %d, Email: %s, Name: %s, Date: %tF\n",
                member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime()
        );
    }
}
