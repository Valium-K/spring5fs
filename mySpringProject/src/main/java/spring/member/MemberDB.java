package spring.member;

import java.util.ArrayList;
import java.util.List;

public class MemberDB {
    private List<Member> memberList;
    public static int count = 0;

    public MemberDB() {}

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }
    //    public MemberDB(List<Member> memberList) {
//        this.memberList = memberList;
//    }

    public List<Member> getMemberList() {
        return memberList;
    }
}
