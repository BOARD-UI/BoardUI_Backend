package edu.escuelaing.arsw.boardUI.model;

public class Position {
    int start;
    int end;

    public Position(){}

    public Position(int start, int end){
        this.start = start;
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("Position { start: %d, end: %d}", start, end);
    }
}
