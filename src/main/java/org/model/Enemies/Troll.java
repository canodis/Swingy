package org.model.Enemies;

public class Troll extends AEnemy {
    private static final int BASE_ATTACK = 20;
    private static final int BASE_DEFENSE = 15;
    private static final int BASE_HP = 200;

    public Troll() {
        super(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
        this.rarity = Rarity.EPIC;
    }
    public Troll(int level, int attack, int defense, int hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83D\uDC79";
        this.rarity = Rarity.EPIC;
    }

    @Override
    public AEnemy Clone(int Level) {
        int scaledAttack = scaleStat(BASE_ATTACK, Level);
        int scaledDefense = scaleStat(BASE_DEFENSE, Level);
        int scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Troll(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }

}
