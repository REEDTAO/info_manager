package com.reed.info_manager.entity;

import com.reed.info_manager.constant.Constant;
import lombok.Data;

@Data
public class PieChartData {
//    value    : 700,
//    color    : '#f56954',
//    highlight: '#f56954',
//    label    : 'Chrome'
    String  value;
    String color;
    String highlight;
    String label;

    public void setValue(String value) {
        this.value = value;
        this.color= Constant.randomColor();
    }
}
