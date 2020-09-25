package chap04.spring;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// 어노테이션에 값을 주지 않으면 클래스의 첫 글자를 소문자로한 이름을 빈으로 사용
@Component
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
