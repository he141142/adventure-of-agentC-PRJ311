/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.Resource;

import customgame.Operation.Entities.Floor;
import customgame.Operation.Entities.Monster;

/**
 *
 * @author Administrator
 */
public class World {
    public static final Floor BASE_LEVEL = new Floor(new String[]{
                "###############",
                "#....##.!.....#",
                "#........p....#",
                "#.$..##.......@",
                "#....######...#",
                "#........##...#",
                "####8######...#",
                "#.............#",
                "#...####......#",
                "#....###......#",
                "@......#......#",
                "#......#......#",
                "###############"
      
    },1,1,
                    new Monster(Monster.Type.BAT, 10, 1),
                    new Monster(Monster.Type.BAT, 8 , 11),
                    new Monster(Monster.Type.BAT, 11, 4),
                    new Monster(Monster.Type.VENOM, 11, 4)
    );
    
    public static final Floor LEVEL_1 =  new Floor(new String[]{
                      "####################",
                      "#.....#......%.....#",
                      "@.........%.....%..#",
                      "#.....######..######",
                      "###.#.#....#..######",
                      "###.#......#..######",
                      "###.########..######",
                      "###................#",
                      "############@#######"
        },18,7,
                      new Monster(Monster.Type.GHOST, 6, 7));
    
    
    
    public static final Floor LEVEL_2 = new Floor(new String[]{
                    "######################",
                    "##...d...!.######!...#",
                    "##.........##..##....#",
                    "##....g............d.#",
                    "##..d......######....#",
                    "##......c..######....#",
                    "###################..#",
                    "#.#########@########8#",
                    "#$.###.........####..#",
                    "#...##.........###...#",
                    "@....................#",
                    "#...##.........#######",
                    "#..###.........#######",
                    "#.######.......#######",
                    "######################"
                    
    },2,1,
                     new Monster(Monster.Type.BAT, 17, 5),
                     new Monster(Monster.Type.VENOM, 17, 5),
                     new Monster(Monster.Type.VENOM, 6, 8),
                     new Monster(Monster.Type.BAT, 8, 13));                     
    
    
    public static final Floor LEVEL_3 = new Floor(new String[]{
                        "##########################",
			"#####@##.....#.#@#...G...#",
			"####...#####.#.%....%....#",
			"####...#...%.#...........#",
			"####...#.%.d.%.....%..%..#",
			"#####.##.###############.#",
			"#%...........###########.#",
			"#####.#####8####.....###.#",
			"##.......#.....#....G###.#",
			"##.##.##.#.....####..###.#",
			"##.#...#.#.....####..###.#",
			"##.......#..v........###.#",
			"##.#...#.#....g..######%.#",
			"##.##.##.#..s....###.##..#",
			"##.......#!......##!.....#",
			"##########################" },
            
            17, 7,      
                       new Monster(Monster.Type.BAT, 5, 6),
                       new Monster(Monster.Type.BAT, 12, 14),
                       new Monster(Monster.Type.BAT, 17, 3));
 /*    public static final Floor LEVEL_4 = new Floor(new String[]{},
            
            0, 0);*/
     
     
      public static final Floor LEVEL_5 = new Floor(new String[]{
                        "#################",
			"#.............#.#",
			"#.####.####%#.#.#",
			"#......##...#.#.#",
			"#.####.#e..G#.#.#",
			"#..#.#.###.##.#.#",
			"#...............#",
			"#.#####.#######.#",
			"#.#...#.......#.#",
			"#.......#..%..#.#",
			"#.#####.#@..$g#.#",
			"#################"
      },
            
            4, 5,
                  new Monster(Monster.Type.VENOM, 10, 6),
                  new Monster(Monster.Type.BAT, 10, 8),
                  new Monster(Monster.Type.BAT, 9, 1));
      
          public static final Floor LEVEL_6 = new Floor(new String[]{
                        "##########################",
			"####...........#.#...G...#",
			"####.........#.%....%....#",
			"####...#...%.####.####.###",
			"####...#.%.d.%p...v%..%..#",
			"####...#.................#",
			"#%.....#.................#",
			"#......#...$.........###.#",
			"##.....###########..G###.#",
			"##...............#########",
			"##........$.............$#",
			"###################.######",
			"##........8..............#",
			"#@........############.###",
			"##................##$...!#",
			"##########################" },
            
            17, 7,      
                       new Monster(Monster.Type.VENOM, 5, 6),
                       new Monster(Monster.Type.BAT, 12, 14),
                       new Monster(Monster.Type.GHOST, 17, 3));
          
           public static final Floor LEVEL_7 = new Floor(new String[]{
                        "##########################",
			"####.........#.#.#...G.p!#",
			"####.........#.%....%....#",
			"####...#...%.#...........#",
			"####...#.%.d.%.....%..%..#",
			"####...#.................#",
			"#%.....##############....#",
			"#......#.............###.#",
			"##.....#.#.....#....G###.#",
			"##.....#.................#",
			"#@.....###############8###",
			"##..................#....#",
			"##########..........#....#",
			"##!......8..........#....#",
			"##.......#.......##.#....#",
			"#######################@##" },
            4, 1,      
                       new Monster(Monster.Type.BAT, 5, 6),
                       new Monster(Monster.Type.BAT, 12, 14),
                       new Monster(Monster.Type.BAT, 17, 3));  
           
           
               public static final Floor LEVEL_8 = new Floor(new String[]{
                        "##########################",
			"####.........#.###...G...#",
			"####.........#.%....%....#",
			"####...#...%.#........G..#",
			"####...#.%.d.%.....%..%..#",
			"####.....####.....#####..#",
			"#%...#...#.....#....G.!..#",
			"#....#...#.....#.....###.#",
			"##...#...#.....#....G###.#",
			"##.......................#",
			"##########.....###########",
			"##................%.......#",
			"##......##.........#####8##",
			"##...p......##....#.......#",
			"##.......#.......##......$#",
			"##@##################@####" },
            
            17, 7,      
                       new Monster(Monster.Type.VENOM, 22, 14),
                       new Monster(Monster.Type.BAT, 17, 3),
                       new Monster(Monster.Type.BAT, 17, 4),
                       new Monster(Monster.Type.BAT, 10, 7),
                       new Monster(Monster.Type.GHOST, 5, 12));
               
                   public static final Floor LEVEL_9= new Floor(new String[]{
                        "##########################",
			"####.........#.###...G...#",
			"####.........#.%....%....#",
			"####...#...%.#...........#",
			"####...#.%.d.%.....%..%..#",
			"####.....................#",
			"#%.......................#",
			"#....................###.#",
			"##.......#.....#....G###.#",
			"##.......................#",
			"##.......................#",
			"##.......................#",
			"##.......................#",
			"##.......................#",
			"##.......#.......##......#",
			"##########################" },
            
            5, 1,      
                       new Monster(Monster.Type.BAT, 5, 6),
                       new Monster(Monster.Type.BAT, 12, 14),
                       new Monster(Monster.Type.BAT, 17, 3));
                      
                       public static final Floor LEVEL_FINISH = new Floor(new String[]{
                        "##########################",
			"####.........#.#@#...G...#",
			"####.........#.%.#..%....#",
			"####...###.%.#...#....#..#",
			"########.%.d.%...#.%..%..#",
			"####.........#...####.#..#",
			"#.....................#..#",
			"#............#.......###.#",
			"######...#####.#########.#",
			"##.......#.......##......#",
			"######...#####.####......#",
			"##.......#.......##......#",
			"##...............##......@",
			"##.......#.......##......#",
			"##.......#.......##......#",
			"##########################" },
            4, 1,      
                       new Monster(Monster.Type.BAT, 5, 6),
                       new Monster(Monster.Type.VENOM, 12, 14),
                       new Monster(Monster.Type.VENOM, 12, 14),
                       new Monster(Monster.Type.VENOM, 12, 14),
                       new Monster(Monster.Type.BAT, 17, 3));
}