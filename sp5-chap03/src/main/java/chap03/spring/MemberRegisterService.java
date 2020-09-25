package chap03.spring;

import chap03.spring.exception.DuplicateMemberException;

import java.time.LocalDateTime;

public class MemberRegisterService {
    private MemberDao memberDao;

    private MemberRegisterService() {}
    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail());

        if(member != null) {
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }

        Member newMember = new Member(
                req.getEmail(), req.getPassword(), req.getName(),
                LocalDateTime.now()
        );


        memberDao.insert(newMember);
        return newMember.getId();
    }
}
