package ru.autodoc.autodocstore.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import ru.autodoc.autodocstore.model.Node;
import ru.autodoc.autodocstore.model.Way;

/**
 * Created by kochetkov.a on 08.12.2015.
 */
public class StubView extends View {
    private List<Node> mNodes;
    private List<Way> mWays;
    private List<Way> mShortestWay;

    private Paint mGreenBrush, mRedBrush, mBlackBrush;

    public StubView(Context context) {
        super(context);
        init();
    }

    public StubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StubView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mGreenBrush = setupBrush(Color.GREEN);

        mRedBrush = setupBrush(Color.RED);
        mRedBrush.setStrokeWidth(3.0f);

        mBlackBrush = setupBrush(Color.BLACK);
    }

    private Paint setupBrush(int color) {
        Paint brush = new Paint();
        brush.setAntiAlias(true);
        brush.setStyle(Paint.Style.STROKE);
        brush.setColor(color);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeCap(Paint.Cap.ROUND);
        brush.setTextSize(25);
        return brush;
    }

    public void setModel(List<Node> nodes, List<Way> ways) {
        mNodes = nodes;
        mWays = ways;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Node node :
                mNodes) {
            canvas.drawCircle(node.getX(), node.getY(), 30, mGreenBrush);
            canvas.drawText(node.text, node.getX(), node.getY(), mBlackBrush);
        }

        for (Way way :
                mWays) {
            canvas.drawLine(way.getStartX(), way.getStartY(), way.getEndX(), way.getEndY(), mBlackBrush);
            canvas.drawText(String.valueOf(way.weight), way.getTextX(), way.getTextY(), mBlackBrush);
        }

        if (mShortestWay == null)
            return;

        for (Way way :
                mShortestWay) {
            canvas.drawLine(way.getStartX(), way.getStartY(), way.getEndX(), way.getEndY(), mRedBrush);
        }
    }


    public void setRoute(List<Way> shortestWay) {
        mShortestWay = shortestWay;
        invalidate();
    }
}
