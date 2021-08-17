//package controller.collisionEngine;
//
//import view.gameField.GameField;
//
//public class PhysicsEngineImpl implements PhysicsEngine {
//
//    private GameField gamefield;
//    private static final int SHIP_RADIUS = 27;
//    private static final int BULLET_RADIUS = 10;
//    private static final int POWERUP_RADIUS = 10;
//
//    public PhysicsEngineImpl(GameField gamefield) {
//        this.gamefield = gamefield;
//
//        // funzioni che puoi usare
//        /*
//         * this.gamefield.getPlayer(); this.gamefield.getActiveEnemyShips();
//         * this.gamefield.getBonusObjects();
//         * 
//         * this.gamefield.getWidth(); this.gamefield.getHeight();
//         * this.gamefield.getActiveBulletsShotbyEnemies();
//         * this.gamefield.getActiveBulletsShotbyPlayer();
//         * 
//         */
//
//    }
//
//    @Override
//    public double checkDistance(final double x1, final double x2, final double y1, final double y2) {
//        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
//    }
//
//    @Override
//    public void removeLife() {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void removePoints() {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void checkIfElementsCollide() {
//        
//        /*
//         * PowerUp
//         */
//        if (SHIP_RADIUS + POWERUP_RADIUS > this.checkDistance(/*ship x*/, /*power up x*/, /*ship y*/, /*power up y*/)) {
//            //set powerup
//            //show hud power up
//        }
//        
//        /*
//         * Enemies
//         */
//        for(/*for each entity active*/) {
//            if (SHIP_RADIUS * 2 > this.checkDistance(/*ship x*/, /*enemy x*/, /*ship y*/, /*enemy y*/))) {
//                //remove life
//                //remove points
//                //chek if dead
//            }
//        }
//        
//        /*
//         * Bullets
//         */
//        for(/*for each entity active*/) {
//            if (SHIP_RADIUS + BULLET_RADIUS> this.checkDistance(/*enemy x*/, /*bullet x*/, /*enemy y*/, /*bullet y*/))) {
//                //destroy enemy
//                //add point
//            }
//        }
//    }
//}
