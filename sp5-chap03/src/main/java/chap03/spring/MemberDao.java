package chap03.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberDao {

    private static long nextId = 0;

    private Map<String, Member> memberMap = new HashMap<>();

    public Collection<Member> selectAll() {
        return memberMap.values();
    }
    public Member selectByEmail(String email) {
        return memberMap.get(email);
    }
    public void insert(Member member) {
        member.setId(++nextId);
        memberMap.put(member.getEmail(), member);
    }
    public void update(Member member) {
        memberMap.put(member.getEmail(), member);
    }
}
