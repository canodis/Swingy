package org.model.Enemies;

public class Medusa extends AEnemy {
    private static final int BASE_ATTACK = 10;
    private static final int BASE_DEFENSE = 5;
    private static final int BASE_HP = 100;

    public Medusa() {
        super(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
        this.rarity = Rarity.COMMON;
    }
    public Medusa(int level, int attack, int defense, int hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83D\uDC0D";
        this.rarity = Rarity.COMMON;
    }

    @Override
    public AEnemy Clone(int Level) {
        int scaledAttack = scaleStat(BASE_ATTACK, Level);
        int scaledDefense = scaleStat(BASE_DEFENSE, Level);
        int scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Medusa(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }
}
