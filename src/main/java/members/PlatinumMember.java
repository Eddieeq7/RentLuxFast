package members;

public class PlatinumMember extends Member {

    public PlatinumMember(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public double getDiscount(double amount) {
        return amount * 0.10;
    }

    @Override
    public boolean canAccessExclusive() {
        return true;
    }
}
