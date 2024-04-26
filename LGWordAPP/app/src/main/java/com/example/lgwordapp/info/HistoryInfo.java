package com.example.lgwordapp.info;

public class HistoryInfo {
    private int history_id;
    private String yword;
    private String tword;

    public HistoryInfo(int history_id, String yword, String tword) {
        this.history_id = history_id;
        this.yword = yword;
        this.tword = tword;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public String getYword() {
        return yword;
    }

    public void setYword(String yword) {
        this.yword = yword;
    }

    public String getTword() {
        return tword;
    }

    public void setTword(String tword) {
        this.tword = tword;
    }
}
