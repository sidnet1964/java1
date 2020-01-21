package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight {
    public CompareResult compareWeight(CompareWeight smthHasWeigt);
    public enum CompareResult {LESS, EQUAL, GREATER};
    //  3.2 В нем создать public enum CompareResult
}
