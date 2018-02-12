package pl.knowakowski.tetris;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by krzysiek on 13.02.2018.
 */

public class ArraySet<E> extends AbstractSet<E> {
    private final ArrayList<E> list = new ArrayList<>();

    @Override
    public boolean add(E e) {
        if (!list.contains(e)) {
            list.add(e);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }
}