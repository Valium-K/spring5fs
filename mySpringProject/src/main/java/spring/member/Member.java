package spring.member;

public class Member {

    private int index;
    private String id;
    private String password;
    private String email;

    public Member(String id, String password, String email) {
        this.index = 0;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
