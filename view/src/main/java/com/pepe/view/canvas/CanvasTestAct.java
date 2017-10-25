package com.pepe.view.canvas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pepe.view.R;


public class CanvasTestAct extends Activity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_view_canvas_test);
    }

    @Override
    public void onClick(View v) {
        CanvasView.DrawMode drawMode;
        switch (v.getId()){
            case R.id.btnDrawAxis:
                drawMode = CanvasView.DrawMode.AXIS;
                break;
            case R.id.btnDrawARGB:
                drawMode = CanvasView.DrawMode.ARGB;
                break;
            case R.id.btnDrawText:
                drawMode = CanvasView.DrawMode.TEXT;
                break;
            case R.id.btnDrawPoint:
                drawMode = CanvasView.DrawMode.POINT;
                break;
            case R.id.btnDrawLine:
                drawMode = CanvasView.DrawMode.LINE;
                break;
            case R.id.btnDrawRect:
                drawMode = CanvasView.DrawMode.RECT;
                break;
            case R.id.btnDrawCircle:
                drawMode = CanvasView.DrawMode.CIRCLE;
                break;
            case R.id.btnDrawOval:
                drawMode = CanvasView.DrawMode.OVAL;
                break;
            case R.id.btnDrawArc:
                drawMode = CanvasView.DrawMode.ARC;
                break;
            case R.id.btnDrawPath:
                drawMode = CanvasView.DrawMode.PATH;
                break;
            case R.id.btnDrawBitmap:
                drawMode = CanvasView.DrawMode.BITMAP;
                break;
            default:
                drawMode = CanvasView.DrawMode.UNKNOWN;
                break;
        }
        Intent intent = new Intent(this, CanvasAct.class);
        intent.putExtra("drawMode", drawMode.value());
        startActivity(intent);
    }
}