package ru.job4j.collection;

import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class JobTest {

    @Test
    public void whenCompareAscByName() {
        Comparator<Job> comparator = new JobAscByName();
        int rsl = comparator.compare(
                new Job("Fix bugs", 2),
                new Job("Impl task", 3));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompareAscByPriority() {
        Comparator<Job> comparator = new JobAscByPriority();
        int rsl = comparator.compare(
                new Job("Fix bugs", 2),
                new Job("Impl task", 3));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompareDescByName() {
        Comparator<Job> comparator = new JobDescByName();
        int rsl = comparator.compare(
                new Job("Fix bugs", 2),
                new Job("Impl task", 3));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompareDescByPriority() {
        Comparator<Job> comparator = new JobDescByPriority();
        int rsl = comparator.compare(
                new Job("Fix bugs", 2),
                new Job("Impl task", 3));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bugs", 2),
                new Job("Fix bugs", 3));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByPriorityAndName() {
        Comparator<Job> cmpNamePriority = new JobAscByPriority().thenComparing(new JobAscByName());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bugs", 2),
                new Job("Fix bugs", 3));
        assertThat(rsl, lessThan(0));
    }
}