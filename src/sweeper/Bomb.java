package sweeper;

class Bomb {

    private Matrix bombMap;
    private int totalBombs;

    public Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixNumbersOfBombs();
    }

    void start(){
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }

    }

    Box get(Coord coord){
        return bombMap.get(coord);
    }

    private void placeBomb(){
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if(Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBombs(coord);
            break;
        }
    }

    private void incNumbersAroundBombs(Coord coord){
        for (Coord around : Ranges.getCoordAround(coord))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    private void fixNumbersOfBombs(){
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if(totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    int getTotalBombs() {
        return totalBombs;
    }
}
