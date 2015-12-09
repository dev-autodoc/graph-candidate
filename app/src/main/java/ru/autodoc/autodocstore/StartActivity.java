package ru.autodoc.autodocstore;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.widget.Button;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import ru.autodoc.autodocstore.core.Solver;
import ru.autodoc.autodocstore.model.Node;
import ru.autodoc.autodocstore.model.Way;
import ru.autodoc.autodocstore.view.StubView;

/**
 * Created by kochetkov.a on 07.12.2015.
 */
public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        TODO:
//        -R.id.btnAnew: возврат данных к начальному состоянию;
//        -R.id.txtVwWeight: выводить вес найденного пути;
//        -в алгоритме поиска допущена логическая ошибка --
//              для какого входного набора алгоритм сломается? Как починить?;
//        -изменить "мгновенное" выделение найденного пути на поэтапное.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //arrange
        Node a = new Node("A");
        a.set(150, 50);
        Node b = new Node("B");
        b.set(50, 150);
        Node c = new Node("C");
        c.set(250, 150);
        Node d = new Node("D");
        d.set(150, 250);
        Node e = new Node("E");
        e.set(450, 250);

        List<Node> nodes = Lists.newArrayList(
                a, b, c, d, e
        );
        List<Way> ways = Lists.newArrayList(
                new Way(new Pair<Node, Node>(a, b), 4),
                new Way(new Pair<Node, Node>(a, c), 7),
                new Way(new Pair<Node, Node>(c, d), 12),
                new Way(new Pair<Node, Node>(c, e), 6),
                new Way(new Pair<Node, Node>(b, d), 11),
                new Way(new Pair<Node, Node>(b, c), 6),
                new Way(new Pair<Node, Node>(e, d), 2)
        );

        StubView stbVw = (StubView) this.findViewById(R.id.stbVw);
        stbVw.setModel(nodes, ways);

        Button btnSolve = (Button) this.findViewById(R.id.btnSolve);

        Solver solver = new Solver(new ArrayList<Way>(ways), a, e);

        btnSolve.setOnClickListener(v ->
                {
                    List<Way> route = Lists.newArrayList();
                    while (solver.hasNext()) {
                        route.add(solver.next());
                    }
                    stbVw.setRoute(route);
                }
        );
    }


}
