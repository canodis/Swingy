package org.model.Enemies;

public class Medusa extends AEnemy {
    private static final float BASE_ATTACK = 20;
    private static final float BASE_DEFENSE = 5;
    private static final float BASE_HP = 100;

    public Medusa() {
        this(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
    }
    public Medusa(int level, float attack, float defense, float hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83D\uDC0D";
        this.rarity = Rarity.COMMON;
        this.Name = "Medusa";
    }

    @Override
    public AEnemy Clone(int Level) {
        float scaledAttack = scaleStat(BASE_ATTACK, Level);
        float scaledDefense = scaleStat(BASE_DEFENSE, Level);
        float scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Medusa(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }
}
