package members;

public class GoldMember extends Member {

    public GoldMember(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public double getDiscount(double amount) {
        return amount * 0.05;
    }

    @Override
    public boolean canAccessExclusive() {
        return false;
    }
}
