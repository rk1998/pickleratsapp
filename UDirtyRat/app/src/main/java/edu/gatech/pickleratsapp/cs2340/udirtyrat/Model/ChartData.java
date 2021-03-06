package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

import android.support.annotation.NonNull;

/**
 * Class that represents a data point for a chart
 */

public class ChartData implements Comparable {
    private final int x;
    private final int y;
    public ChartData(int px, int py) {
        x = px;
        y = py;
    }
    private int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    @Override
    public int compareTo(@NonNull Object o) {
        ChartData d = (ChartData) o;
        return x - d.getX();
    }
}
