package spring.member;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberDao {

    private MemberDB memberDB;

    public MemberDao(MemberDB memberDB) {
        this.memberDB = memberDB;
    }



    // c
    public void insert(Member member) {
        member.setIndex(MemberDB.count++);
        memberDB.getMemberList().add(member);
    }

    // r
    public Member selectByEmail(String email) {
        return memberDB.getMemberList().stream().filter(m -> email.equals(m.getEmail())).findFirst().orElse(null);
    }
    public Member selectById(String id) {
        return memberDB.getMemberList().stream().filter(m -> id.equals(m.getId())).findFirst().orElse(null);
    }
    public List<Member> selectAll() {
        return memberDB.getMemberList();
    }

//    public int count() {
//        return 0;
//    }

    // u
    public void update(Member member) {

        Member targetMember = memberDB.getMemberList().get(member.getIndex());
        targetMember.setEmail(member.getEmail());
        targetMember.setPassword(member.getPassword());
    }

    // d
    public void delete(int index) {
        memberDB.getMemberList().remove(index);
        MemberDB.count--;
    }



}
