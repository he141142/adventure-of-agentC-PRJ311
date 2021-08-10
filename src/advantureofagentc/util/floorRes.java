/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package advantureofagentc.util;

import advantureofagentc.operation.level.Floor;
import java.util.ArrayList;
import advantureofagentc.operation.entities.Monster;

/**
 *
 * @author Administrator
 */
public class floorRes {

    public static Floor getFloor_base() {
        return floor_base;
    }

    public static Floor getLEVEL_2() {
        return LEVEL_2;
    }

    public static Floor getLEVEL_3() {
        return LEVEL_3;
    }
    public static final Floor floor_base = new Floor(new String[]{
          "#####################",
          "#...................#",
          "######.........#....#",
          "#..............#....#",
          "#..............#....#",
          "#..............#....#",
          "#....###########....#",
          "#....###########....#",
          "#..............#....#",
          "#.........######....#",
          "#..........#####...##",
          "#...................#",
          "#####################"
    },"base",1,2);
   
   public static final Floor LEVEL_2 = new Floor (new String[]{
                 "########################",
                 "#......#.............###",
                 "#......#...#.........###",
                 "#......#...#..#........#",
                 "#..........#..#........#",
                 "#..........#..#......###",
                 "#......#...##.#.......##",
                 "#############.........@#",
                 "#############.........##",
                 "###........%..........##",
                 "###.%..........#########",
                 "###.....%.............##",
                 "########################"
     },"leve2",22,12,
                new Monster(Monster.Type.BAT,4,2));
   
   public static final Floor LEVEL_3 = new Floor(new String[]{
                 "########################",
                 "#......#.............###",
                 "#......#...#.........###",
                 "#..#####...#..#........#",
                 "#..#.......#..#........#",
                 "#..##.###...#..####...##",
                 "#......#...##.##......##",
                 "#############.#.......@#",
                 "#############.######..##",
                 "###....####%.......#..##",
                 "###.%..##......#####..##",
                 "###.....%......##.%...##",
                 "########################"
    },"level3",21,2,
                   new Monster(Monster.Type.GHOST,2,4),
                   new Monster(Monster.Type.BAT,19,5),
                   new Monster(Monster.Type.RAT,17,3)
                     );
    
    
    public ArrayList<Floor> tempFloor(){
    ArrayList<Floor> temp = new ArrayList<>();
    temp.add(LEVEL_2);
    temp.add(LEVEL_3);
    return temp;
    }
}
