package org.model.Enemies;

public class Dragon extends AEnemy {
    private static final int BASE_ATTACK = 15;
    private static final int BASE_DEFENSE = 10;
    private static final int BASE_HP = 150;

    public Dragon() {
        super(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
        this.rarity = Rarity.LEGENDARY;
    }

    public Dragon(int level, int attack, int defense, int hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83D\uDC09";
        this.rarity = Rarity.LEGENDARY;
    }

    @Override
    public AEnemy Clone(int Level) {
        int scaledAttack = scaleStat(BASE_ATTACK, Level);
        int scaledDefense = scaleStat(BASE_DEFENSE, Level);
        int scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Dragon(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }
}
