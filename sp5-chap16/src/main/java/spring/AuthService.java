package spring;

// Email과 PW 일치여부를 확인 후 AuthInfo객체를 생성하는 클래스
public class AuthService {
    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public AuthInfo authenticate(String email, String password) {
        Member member = memberDao.selectByEmail(email);

        if(member == null) {
            throw new WrongIdPasswordException();
        }
        if(!member.matchPassword(password)) {
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(member.getId(), member.getEmail(), member.getName());
    }
}
