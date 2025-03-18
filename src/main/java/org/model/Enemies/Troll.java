package org.model.Enemies;

public class Troll extends AEnemy {
    private static final int BASE_ATTACK = 30;
    private static final int BASE_DEFENSE = 15;
    private static final int BASE_HP = 200;

    public Troll() {
        this(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
    }
    public Troll(int level, float attack, float defense, float hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83D\uDC79";
        this.rarity = Rarity.EPIC;
        this.Name = "Troll";
    }

    @Override
    public AEnemy Clone(int Level) {
        float scaledAttack = scaleStat(BASE_ATTACK, Level);
        float scaledDefense = scaleStat(BASE_DEFENSE, Level);
        float scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Troll(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }

}
