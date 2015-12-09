package ru.autodoc.autodocstore.core;

import android.graphics.Point;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ru.autodoc.autodocstore.model.Node;
import ru.autodoc.autodocstore.model.Way;

/**
 * Created by kochetkov.a on 08.12.2015.
 */
public class Solver implements Iterator<Way> {

    private List<Way> mWays;
    private Node mStart, mEnd;
    private Way mCurrent;

    public Solver(List<Way> ways, Node start, Node end){
        mWays = ways;
        mStart = start;
        mEnd = end;
    }

    @Override
    public boolean hasNext() {
        return mCurrent == null || !mCurrent.contains(mEnd);
    }

    @Override
    public Way next() {
        Iterable<Way> connections = Iterables.filter(mWays,
                (Way w) -> w.way.first == mStart || w.way.second == mStart
        );
        mCurrent = Collections.min(Lists.newArrayList(connections),
                (Way lhs, Way rhs) -> {
                    if (lhs.weight < rhs.weight)
                        return -1;
                    else if(lhs.weight > rhs.weight)
                        return 1;
                    return 0;
                }
        );
        mWays.remove(mCurrent);
        mStart = mCurrent.way.second;
        return mCurrent;
    }

    @Override
    public void remove() {
        //TODO
    }
}
