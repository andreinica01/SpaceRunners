package GameObjects;

public class Bullet extends EntityImpl {
    
    private final int damage;
    Bullet(int bulletDamage)
    {
        this.damage = bulletDamage; 

    }


    int getBulletDamage()
    {
        return this.damage;


    }
}
