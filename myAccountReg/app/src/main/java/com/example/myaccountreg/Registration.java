package com.example.myaccountreg;

import java.util.ArrayList;
import java.util.List;

public class Registration {
    private List<Row> rows;
    public Registration(){
        this.rows = new ArrayList<>();
    }
    public List<Row> getRows() {
        return rows;
    }
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
