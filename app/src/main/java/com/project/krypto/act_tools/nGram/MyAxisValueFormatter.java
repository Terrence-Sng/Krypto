package com.project.krypto.act_tools.nGram;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by pandaboy_13 on 3/30/2017.
 */

public class MyAxisValueFormatter implements IAxisValueFormatter{
        private int val = 0;
        private String [] myVal;
        public MyAxisValueFormatter(String[] values)
        {
            myVal = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            if(val >= myVal.length)
            {
                val = 0;
            }
            value = val;
            val++;
            System.out.println(value);
            return myVal[(int)value%myVal.length];

        }

        // we don't draw numbers, so no decimal digits needed
        public int getDecimalDigits() {  return 0; }
}
