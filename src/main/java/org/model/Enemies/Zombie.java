package org.model.Enemies;

public class Zombie extends AEnemy{
    private static final float BASE_ATTACK = 20;
    private static final float BASE_DEFENSE = 5;
    private static final float BASE_HP = 300;

    public Zombie() {
        this(1, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
    }
    public Zombie(int level, float attack, float defense, float hp) {
        super(level, attack, defense, hp);
        this.Icon = "\uD83E\uDDDF";
        this.rarity = Rarity.RARE;
        this.Name = "Zombie";
    }

    @Override
    public AEnemy Clone(int Level) {
        float scaledAttack = scaleStat(BASE_ATTACK, Level);
        float scaledDefense = scaleStat(BASE_DEFENSE, Level);
        float scaledHp = scaleStat(BASE_HP, Level);

        AEnemy clone = new Zombie(Level, scaledAttack, scaledDefense, scaledHp);
        clone.rarity = this.rarity;
        return clone;
    }
}
