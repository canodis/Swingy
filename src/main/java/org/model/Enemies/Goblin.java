package org.model.Enemies;

public class Goblin extends AEnemy{
    private static final float BASE_ATTACK = 20;
    private static final float BASE_DEFENSE = 2;
    private static final float BASE_HP = 120;

    public Goblin() {
        this(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
    }
    public Goblin(int level, float attack, float defense, float hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83D\uDC7A";
        this.rarity = Rarity.UNCOMMON;
        this.Name = "Goblin";
    }

    @Override
    public AEnemy Clone(int Level) {
        float scaledAttack = scaleStat(BASE_ATTACK, Level);
        float scaledDefense = scaleStat(BASE_DEFENSE, Level);
        float scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Goblin(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }
}
