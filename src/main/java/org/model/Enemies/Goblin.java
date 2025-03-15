package org.model.Enemies;

public class Goblin extends AEnemy{
    private static final int BASE_ATTACK = 5;
    private static final int BASE_DEFENSE = 2;
    private static final int BASE_HP = 50;

    public Goblin() {
        super(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
        this.rarity = Rarity.LEGENDARY;
    }
    public Goblin(int level, int attack, int defense, int hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83D\uDC7A";
        this.rarity = Rarity.UNCOMMON;
    }

    @Override
    public AEnemy Clone(int Level) {
        int scaledAttack = scaleStat(BASE_ATTACK, Level);
        int scaledDefense = scaleStat(BASE_DEFENSE, Level);
        int scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Goblin(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }
}
