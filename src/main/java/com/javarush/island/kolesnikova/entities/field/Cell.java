package com.javarush.island.kolesnikova.entities.field;

import com.javarush.island.kolesnikova.constants.PropertiesUnit;
import com.javarush.island.kolesnikova.entities.units.Unit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Set;

@Getter
@Setter
@ToString
public class Cell {

    private int x;
    private int y;


    private HashMap<PropertiesUnit.UnitsName, Set<Unit>> unitsInCell;

    public Cell(int x, int y, HashMap<PropertiesUnit.UnitsName, Set<Unit>> unitsInCell) {
        this.x = x;
        this.y = y;
        this.unitsInCell = unitsInCell;
//        System.out.printf("\n\nC������ ������ %d | %d � ��� ��������: ", x, y);
    }

    public Set<Unit> getSetUnitsInCell(PropertiesUnit.UnitsName unitsName) {
        return unitsInCell.get(unitsName);
    }






}
