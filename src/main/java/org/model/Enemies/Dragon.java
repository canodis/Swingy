package org.model.Enemies;

public class Dragon extends AEnemy {
    private static final float BASE_ATTACK = 50;
    private static final float BASE_DEFENSE = 10;
    private static final float BASE_HP = 500;

    public Dragon() {
        this(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
    }

    public Dragon(int level, float attack, float defense, float hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83D\uDC09";
        this.rarity = Rarity.LEGENDARY;
        this.Name = "Dragon";
    }

    @Override
    public AEnemy Clone(int Level) {
        float scaledAttack = scaleStat(BASE_ATTACK, Level);
        float scaledDefense = scaleStat(BASE_DEFENSE, Level);
        float scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Dragon(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }
}
