public abstract class Character {
    private int hitPoints;
    private int armorClass;
    private WeaponBehavior weaponBehavior;

    Character(int initialHitPoints, int armorClass, WeaponBehavior initialWeapon) {
        hitPoints = initialHitPoints;
        this.armorClass = armorClass;
        weaponBehavior = initialWeapon;
    }

    public void fight() {
        if(hitPoints > 0) {
            weaponBehavior.useWeapon();
        }
        else {
            System.out.println("Is down for the count...");
        }
    }

    public void setWeapon(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void takeDamage(int damage) {
        hitPoints = Math.max(hitPoints - damage, 0);
    }
}