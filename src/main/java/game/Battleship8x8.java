package game;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    /*
     * получить индекс выстрела в массиве (например, для А1 - 0, В2 - 10)
     */
    public int getIndexOfShot(String shot) {
        String[] cutShots = shot.split("");

        int row = Integer.parseInt(cutShots[1]);

        Map<String, Integer> columnsMap = new HashMap<>();
        columnsMap.put("A", 1);
        columnsMap.put("B", 2);
        columnsMap.put("C", 3);
        columnsMap.put("D", 4);
        columnsMap.put("E", 5);
        columnsMap.put("F", 6);
        columnsMap.put("G", 7);
        columnsMap.put("H", 8);

        int col = columnsMap.get(cutShots[0]);

        return (row - 1) * 8 + col - 1;
    }

    /*
     * делаем выстрел: если попали, возвращаем true, мимо - false
     * здесь же делаем long для выстрелов в формате 000100100, где 1 - выстрел
     * (индексы соответствуют ships)
     */
//    Registers a shot and returns `true` if the shot hits a ship.
//    A shot is a combination of one of A-H letters and one of 1-8 digits
    public boolean shoot(String shot) {
        int indexOfShot = getIndexOfShot(shot);
        StringBuilder stringShots = getFullLength(Long.toBinaryString(shots));
        stringShots.replace(indexOfShot, indexOfShot + 1, "1");
        shots = new BigInteger(String.valueOf(stringShots), 2).longValue();

        String map = getFullLength(Long.toBinaryString(ships)).toString();
        return String.valueOf(map.charAt(indexOfShot)).equals("1");
    }

    /*
     * отображаем состояние поля
     * так же сранвиваем по индексам ships и shots, если shots[i] = 1, то выстрел по кораблям (или мимо)
     * и заменяем в ships все 0 и 1 на нужные символы для отображения поля
     */
//    Returns a string representing state of the map.
//    Map string is 8 lines per 8 characters separated by "\n". Use following symbols:
//  - '.' - an empty cell
//  - '×' - an empty cell that has been shot
//  - '☐' - a cell seized by a ship
//  - '☒'- a cell seized by a ship that has been shot
    public String state() {
        String map = getFullLength(Long.toBinaryString(ships)).toString();
        char[] mapArray = map.toString().toCharArray();
//        StringBuilder newMap = new StringBuilder(map.replaceAll("1", "☐").replaceAll("0", "."));
//
//        for(Map.Entry<String, Boolean> entry : changes.entrySet()) {
//            int ind = getIndexOfShot(entry.getKey());
//            if (entry.getValue()) {
//                newMap.replace(ind, ind + 1, "☒");
//            }
//            else{
//                newMap.replace(ind, ind + 1, "×");
//            }
//        }

        String binaryShots = getFullLength(Long.toBinaryString(shots)).toString();
        char[] binaryShotsArray = binaryShots.toCharArray();
        for (int i = 0; i < binaryShots.length(); i++) {
            if (String.valueOf(binaryShotsArray[i]).equals("1")) {
                String symbol = String.valueOf(mapArray[i]).equals("1") ? "☒" : "×";
                map = new StringBuilder(map).replace(i, i + 1, symbol).toString();
            }
        }
        map = map.replaceAll("1", "☐").replaceAll("0", ".");

//        char[] newMapChar = newMap.toString().toCharArray();
//        int ind = 0;
//        System.out.println("______________________");
//        for (int i = 0; i < 8; i++){
//            for (int j = 0; j < 8; j++){
//                System.out.print(newMapChar[ind]);
//                ind++;
//            }
//            System.out.println();
//        }
        return String.join("\n",
                map.substring(0,8), map.substring(8,16),
                map.substring(16,24), map.substring(24,32),
                map.substring(32,40), map.substring(40,48),
                map.substring(48,56), map.substring(56,64));
    }

    public void printMap() {
        String map = Long.toBinaryString(ships);
        char[] newMap = map.replaceAll("1", "☐").replaceAll("0", ".").toCharArray();
        System.out.println(newMap);
        int ind = 0;
        System.out.println("  | ABCDEFGH");
        for (int i = 0; i < 8; i++){
            System.out.print(i+1 + " | ");
            for (int j = 0; j < 8; j++){
                System.out.print(newMap[ind]);
                ind++;
            }
            System.out.println();
        }
    }

    /*
     * получаем полную длину для бинарной строки, полученной из long (длина должна быть равна 64 символам)
     */
    private StringBuilder getFullLength(String string) {
//        StringBuilder stringBuilder = new StringBuilder(string);
//        new StringBuilder(string).insert(0, "0".repeat(64 - stringBuilder.length()));
        return new StringBuilder(string).insert(0, "0".repeat(64 - string.length()));
    }
}
