package com.gmail.gorbasenko245.model;

import java.util.Objects;

public class TestObj {
    private String string;
    private int anInt;
    private int hash;

    public TestObj(String string, int anInt, int hash) {
        this.string = string;
        this.anInt = anInt;
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObj testObj = (TestObj) o;
        return anInt == testObj.anInt &&
                Objects.equals(string, testObj.string);
    }

    @Override
    public int hashCode() {
        return 12;
    }

    @Override
    public String toString() {
        return "TestObj{" +
                "string='" + string + '\'' +
                ", anInt=" + anInt +
                ", hash=" + hash +
                '}';
    }
}
