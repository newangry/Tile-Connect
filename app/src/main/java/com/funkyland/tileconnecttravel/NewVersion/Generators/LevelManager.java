package com.funkyland.tileconnecttravel.NewVersion.Generators;

public class LevelManager {

    public static int levelCurrent = 1;
    public static int totalLevel = Integer.MAX_VALUE;

    public static int getStarByLevel(final int n, final int n2) {
        int n3 = 0;
        final int n4 = getTimeLevel() / 5;
        if (n2 <= n4 * 2) {
            n3 = 3;
        } else if (n2 <= n4 * 3) {
            n3 = 2;
        } else if (n2 <= n4 * 4) {
            n3 = 1;
        }
        return n3;
    }

    public static int getTimeLevel() {

        int time = 0;

        if (LevelManager.levelCurrent <= 15) {
//            if (Pereferences.get_gameType().equals(GAME_TYPE_EASY)) {
//                time = (int) (3 * (MTMap.row * MTMap.column) - 4 * (-1 + LevelManager.levelCurrent));
//                return (time * 2);
//            } else if (Pereferences.get_gameType().equals(GAME_TYPE_HARD)) {
//                time = (int) (3 * (MTMap.row * MTMap.column) - 4 * (-1 + LevelManager.levelCurrent));
//                return time;
//            }
            return (int) (3 * (MTMap.row * MTMap.column) - 4 * (-1 + LevelManager.levelCurrent));
        }

        if (LevelManager.levelCurrent <= 20) {
//            if (Pereferences.get_gameType().equals(GAME_TYPE_EASY)) {
//                time = (int) (0.95 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
//                return (time * 2);
//            } else if (Pereferences.get_gameType().equals(GAME_TYPE_HARD)) {
//                time = (int) (0.95 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
//                return time;
//            }
            return (int) (0.95 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
        }

        if (LevelManager.levelCurrent <= 40) {
//            if (Pereferences.get_gameType().equals(GAME_TYPE_EASY)) {
//                time = (int) (0.90 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
//                return (time * 2);
//            } else if (Pereferences.get_gameType().equals(GAME_TYPE_HARD)) {
//                time = (int) (0.90 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
//                return time;
//            }
            return (int) (0.90 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
        }

        if (LevelManager.levelCurrent <= 80) {
//            if (Pereferences.get_gameType().equals(GAME_TYPE_EASY)) {
//                time = (int) (0.85 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
//                return (time * 2);
//            } else if (Pereferences.get_gameType().equals(GAME_TYPE_HARD)) {
//                time = (int) (0.85 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
//                return time;
//            }
            return (int) (0.85 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
        }

//        if (Pereferences.get_gameType().equals(GAME_TYPE_EASY)) {
//            time = (int) (0.80 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
//            return (time * 2);
//        } else if (Pereferences.get_gameType().equals(GAME_TYPE_HARD)) {
//            time = (int) (0.80 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
//            return time;
//        }

        return (int) (0.80 * (3 * (MTMap.row * MTMap.column) - 4 * (-1 + 15)));
    }
}