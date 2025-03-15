package org.model.Enemies;

public class Zombie extends AEnemy{
    private static final int BASE_ATTACK = 5;
    private static final int BASE_DEFENSE = 5;
    private static final int BASE_HP = 50;

    public Zombie() {
        super(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
        this.rarity = Rarity.RARE;
    }
    public Zombie(int level, int attack, int defense, int hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83E\uDDDF";
        this.rarity = Rarity.RARE;
    }

    @Override
    public AEnemy Clone(int Level) {
        int scaledAttack = scaleStat(BASE_ATTACK, Level);
        int scaledDefense = scaleStat(BASE_DEFENSE, Level);
        int scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Zombie(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }
}
