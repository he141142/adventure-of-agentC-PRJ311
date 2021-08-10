/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Operation.Entities;

/**
 *
 * @author Administrator
 */
public class Monster extends EntityObject {
    private Type type;
    public boolean huntPlayer;
    
    public Monster(Type type, int worldPosX, int worldPosY) {
        super(type.getName(),worldPosX,worldPosY,type.getHp());
        this.setDeffence(type.getDef());
        this.setStrength(type.getAtk());
        this.type = type;
        this.huntPlayer = type.huntHuman;
    }
    
    public enum Type{
       BAT("bat",2,2,7,false),
       GHOST("ghost",3,5,8,true),
       VENOM("venom",4,2,7,true);
       
       
       private String name;
       private int hp;
       private int def;
       private int atk;
       boolean huntHuman;
       Type( String name ,int atk,int def,int hp,boolean huntPlayer){
          this.name=name;
          this.atk=atk;
          this.def=def;
          this.hp=hp;
          this.huntHuman=huntPlayer;
       }

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

        public int getDef() {
            return def;
        }

        public void setDef(int def) {
            this.def = def;
        }

        public int getAtk() {
            return atk;
        }

        public void setAtk(int atk) {
            this.atk = atk;
        }

        public boolean isHuntHuman() {
            return huntHuman;
        }

        public void setHuntHuman(boolean huntHuman) {
            this.huntHuman = huntHuman;
        }
     
    }
    
}
