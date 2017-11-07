package edu.gatech.pickleratsapp.cs2340.udirtyrat.Model;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Class that formats labels for the graph
 */

public class XAxisDateFormatter implements IAxisValueFormatter {
    private String[] mValues;

    public XAxisDateFormatter(String[] values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mValues[(int) value];
    }
}
