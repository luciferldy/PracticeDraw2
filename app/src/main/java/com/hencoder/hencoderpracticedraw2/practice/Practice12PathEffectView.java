package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    PathEffect discrete;
    PathEffect dash;
    PathEffect pathDash;
    PathEffect sum;
    PathEffect compose;

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);

        discrete = new DiscretePathEffect(20, 5);
        dash = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        Path rectangle = new Path();
        rectangle.rLineTo(5, 10);
        rectangle.rLineTo(-10, 0);
        rectangle.close();
        pathDash = new PathDashPathEffect(rectangle, 40, 10, PathDashPathEffect.Style.TRANSLATE);
        PathEffect dash1 = new DashPathEffect(new float[]{20, 10}, 0);
        PathEffect discrete1 = new DiscretePathEffect(20, 5);
        sum = new SumPathEffect(dash1, discrete1);
        PathEffect dash2 = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        PathEffect discrete2 = new DiscretePathEffect(20, 5);
        compose = new ComposePathEffect(dash2, discrete2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect
        paint.setPathEffect(discrete);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        paint.setPathEffect(dash);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        paint.setPathEffect(pathDash);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        paint.setPathEffect(sum);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        paint.setPathEffect(compose);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
