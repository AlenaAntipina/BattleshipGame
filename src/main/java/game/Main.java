package game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        long map1 = 0b11110000_00000111_00000000_00110000_00000010_01000000_00000000_00000000L;
        List<String> shots1 = List.of("A1", "B2", "C3", "D4");

        long map2 = 0b11110000_00000111_00000000_00110000_00000010_01000000_00000000_00000000L;
        List<String> shots2 = List.of("A1", "B1", "C1", "D1");

        long map3 = 0b11110000_00000111_00000000_00110000_00000010_01000000_00000000_00000000L;
        List<String> shots3 = List.of("A1", "A2", "A3", "A4");

        long map4 = 0b11110000_00000111_00000000_00110000_00000010_01000000_00000000_00000000L;
        List<String> shots4 = List.of("C4", "D4", "E4", "F4");

        long map5 = 0b11110000_00000111_00000000_00110000_00000010_01000000_00000000_00000000L;
        List<String> shots5 = List.of("A1", "B2", "C3", "D4", "E5", "F6", "G7");

        long map6 = 0b01000000_01000000_01000000_01000000_00001110_00000001_00000100_00000000L;
        List<String> shots6 = List.of("A1", "B7", "A5", "H4", "H1", "D3");

        long map7 = 0b01010000_01000010_01000100_01000000_00001110_00110001_10000100_11100000L;
        List<String> shots7 = List.of("A3", "D7", "A5", "F3", "H8", "D3", "B3", "G8", "C3");


//        System.out.println("Battleship_v2");
//        Battleship_v2 battle = new Battleship_v2(map);
//        battle.printMap();

//        shots.forEach(battle::getIndexOfShot);
//        Map<String, Boolean> changesInMap = new HashMap<>();
//        shots.forEach(shot -> changesInMap.put(shot, battle.shoot(shot)));
//        System.out.println(changesInMap);
//        shots.forEach(battle::shoot);
//        System.out.println(battle.state());

        long map = map7;
        List<String> shots = shots7;
        System.out.println("Battleship8x8");
        Battleship8x8 battle = new Battleship8x8(map);
        System.out.println(shots);
//        shots.forEach(battle::shoot);
        Map<String, Boolean> changesInMap = new HashMap<>();
        shots.forEach(shot -> changesInMap.put(shot, battle.shoot(shot)));
        System.out.println(battle.state());
        System.out.println(changesInMap);
    }
}
