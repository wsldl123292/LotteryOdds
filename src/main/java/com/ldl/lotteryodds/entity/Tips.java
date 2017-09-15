package com.ldl.lotteryodds.entity;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-09-12 23:21
 */
public class Tips {

    private String author;
    private Integer day;

    private String match;

    private String result;

    private String score;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tips tips = (Tips) o;

        return day.equals(tips.day) && match.equals(tips.match) && result.equals(tips.result);
    }

    @Override
    public int hashCode() {
        int result1 = day.hashCode();
        result1 = 31 * result1 + match.hashCode();
        result1 = 31 * result1 + result.hashCode();
        return result1;
    }

    @Override
    public String toString() {
        return "Tips{" +
                "author='" + author + '\'' +
                ", day=" + day +
                ", match='" + match + '\'' +
                ", result='" + result + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}


