package members;

public abstract class Member {

    private String memberId;
    private String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public abstract double getDiscount(double amount);
    public abstract boolean canAccessExclusive();

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
}
