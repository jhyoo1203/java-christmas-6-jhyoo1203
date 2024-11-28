package christmas.domain.promotion;

public enum EventBadge {

    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minimumBenefit;

    EventBadge(String name, int minimumBenefit) {
        this.name = name;
        this.minimumBenefit = minimumBenefit;
    }

    public static EventBadge from(int benefitAmount) {
        if (benefitAmount >= SANTA.minimumBenefit) {
            return SANTA;
        }
        if (benefitAmount >= TREE.minimumBenefit) {
            return TREE;
        }
        if (benefitAmount >= STAR.minimumBenefit) {
            return STAR;
        }
        return NONE;
    }

    public String getName() {
        return name;
    }
}
