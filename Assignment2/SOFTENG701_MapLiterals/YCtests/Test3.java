public class Test3 {

    private Weapon gun = new Weapon();

    public void game() {
        for (int i = 0; i < 10; i++) {
            gun.fire();
        }
        gun.ammo = 6;
        a = 0;
    }

    class Weapon {

        private int ammo = 6;

        public void fire() {
            if (ammo > 0) {
                ammo--;
            } else {
            }
        }
    }

    private int a;
}
