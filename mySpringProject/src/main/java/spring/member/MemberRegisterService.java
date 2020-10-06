package spring.member;

import org.springframework.beans.factory.annotation.Autowired;
import spring.member.Exceptions.DuplicationException;
import spring.member.Exceptions.NotMatchingPWException;

public class MemberRegisterService {

    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService() {}
//    public MemberRegisterService(MemberDao memberDao) {
//        this.memberDao = memberDao;
//    }

    public int regist(RegisterRequest request) {
//        if(memberDao.selectByEmail(request.getEmail()) != null)
//            throw new DuplicationException("dup email" + request.getEmail());
        if(memberDao.selectById(request.getId()) != null)
            throw new DuplicationException("dup Id" + request.getId());
        if(!request.getPassword().equals(request.getPasswordConfirm()))
            throw new NotMatchingPWException("no mathcing pw" + request.getId());
        Member newMember = new Member(request.getId(), request.getPassword(), request.getEmail());
        memberDao.insert(newMember);

        return newMember.getIndex();
    }
}
