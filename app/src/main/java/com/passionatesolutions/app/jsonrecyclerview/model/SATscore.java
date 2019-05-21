package com.passionatesolutions.app.jsonrecyclerview.model;

public class SATscore {


    private String dbn;
    private String satCriticalReadingAvgScore;
    private String satMathAvgScore;
    private String satWritingAvgScore;
    private String schoolName;

    public SATscore(){}

    // Returns SAT scores of Reading, Math, and Writing + Dbn (school Id) and school name

    public SATscore(String dbn, String satCriticalReadingAvgScore, String satMathAvgScore, String satWritingAvgScore, String schoolName) {
        this.dbn = dbn;
        this.satCriticalReadingAvgScore = satCriticalReadingAvgScore;
        this.satMathAvgScore = satMathAvgScore;
        this.satWritingAvgScore = satWritingAvgScore;
        this.schoolName = schoolName;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getSatCriticalReadingAvgScore() {
        return satCriticalReadingAvgScore;
    }

    public void setSatCriticalReadingAvgScore(String satCriticalReadingAvgScore) {
        this.satCriticalReadingAvgScore = satCriticalReadingAvgScore;
    }

    public String getSatMathAvgScore() {
        return satMathAvgScore;
    }

    public void setSatMathAvgScore(String satMathAvgScore) {
        this.satMathAvgScore = satMathAvgScore;
    }

    public String getSatWritingAvgScore() {
        return satWritingAvgScore;
    }

    public void setSatWritingAvgScore(String satWritingAvgScore) {
        this.satWritingAvgScore = satWritingAvgScore;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
