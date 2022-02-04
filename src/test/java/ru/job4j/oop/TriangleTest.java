package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void when00and40and04Then8() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        double expected = 8;
        double rsl = triangle.area();
        Assert.assertEquals(expected, rsl, 0.01);
    }

    @Test
    public void when00and40and03Then6() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(0, 3);
        Triangle triangle = new Triangle(a, b, c);
        double expected = 6;
        double rsl = triangle.area();
        Assert.assertEquals(expected, rsl, 0.01);
    }

    @Test
    public void when00and50and90Then6() {
        Point a = new Point(0, 0);
        Point b = new Point(5, 0);
        Point c = new Point(9, 0);
        Triangle triangle = new Triangle(a, b, c);
        double expected = -1;
        double rsl = triangle.area();
        Assert.assertEquals(expected, rsl, 0.01);
    }
}