package com.sapegin.structers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class LinkedListWithHavingName<T extends HavingName> extends ArrayList<T> {

    @Override
    public boolean add(T t) {
        super.add(t);

        this.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return true;
    }

    @Override
    public void add(int index, T element) {
        super.add(index, element);

        this.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T cc : c) {
            add(cc);
        }
        return true;
    }

    public T getByName(String name) {
        for (T t : this) {
            if (t.getName().equals(name)) {
                return (T) t;
            }
        }
        return null;
    }
}
