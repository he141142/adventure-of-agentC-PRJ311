/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.operation.entities;

/**
 *
 * @author Administrator
 */

public class Monster extends EntityTile {
     private Type type;
     private boolean huntPlayer;
    public Monster(Type type,int posX,int posY){
       super(type.getName(), posX, posY, type.getHp());
       this.strength=type.getAtk();
       this.defence=type.getDef();
       this.huntPlayer=type.isHuntPlayer();
    }
    public enum Type {
         BAT("death", 7, 2, 0, false),
         RAT("orc", 11, 2, 0, true),
         GHOST("ghost", 13, 3, 1, true);
       private String name;
       private int hp;
       private int atk;
       private int def;
       private boolean huntPlayer;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public int getAtk() {
            return atk;
        }

        public void setAtk(int atk) {
            this.atk = atk;
        }

        public int getDef() {
            return def;
        }

        public void setDef(int def) {
            this.def = def;
        }

        public boolean isHuntPlayer() {
            return huntPlayer;
        }

        public void setHuntPlayer(boolean huntPlayer) {
            this.huntPlayer = huntPlayer;
        }
      

         Type(String name, int hp, int atk, int def, boolean huntPlayer) {
            this.name = name;
            this.hp = hp;
            this.atk = atk;
            this.def = def;
            this.huntPlayer = huntPlayer;
        }
    }
}
