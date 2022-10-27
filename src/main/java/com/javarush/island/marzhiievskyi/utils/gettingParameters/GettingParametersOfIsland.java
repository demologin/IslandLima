package com.javarush.island.marzhiievskyi.utils.gettingParameters;


public class GettingParametersOfIsland {
    private  int rowsGameField;
    private  int columnsGameField;

    private  int period;

    private  int delayToStart;


    public int getRows() {
        return rowsGameField;
    }

    public int getDelayToStart() {
        return delayToStart;
    }

    public void setDelayToStart(int delayToStart) {
        this.delayToStart = delayToStart;
    }

    public void setRows(int rows) {
        this.rowsGameField = rows;
    }

    public int getColumns() {
        return columnsGameField;
    }

    public void setColumns(int columns) {
        this.columnsGameField = columns;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "ParametersOfIsland{" +
                "rowsGameField=" + rowsGameField +
                ", columnsGameField=" + columnsGameField +
                ", period=" + period +
                ", delayToStart=" + delayToStart +
                '}';
    }
}
