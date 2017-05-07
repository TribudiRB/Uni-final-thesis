package com.bcuzek.magisterka.layout.circuitTimer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.layout.SeekCircuit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircuitTimer extends RelativeLayout {

    @BindView(R.id.text)
    TextView hourTextView;

    @BindView(R.id.seekArc)
    SeekCircuit circuit;

    @BindView(R.id.layout)
    RelativeLayout layout;

    public CircuitTimer(Context context) {
        super(context);
        init(context);
    }

    public CircuitTimer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void updateBarPositionAndHour(long current, String timeToPrint) {
        circuit.printNewPosition(current);
        circuit.invalidate();
        hourTextView.setText(timeToPrint);
    }

    private void init(Context context) {
        ButterKnife.bind(this, inflate(context, R.layout.circuit_timer, this));
        circuit.setArcRotation(0);
    }

    public void selectTimeDialog(final IOnTextClickListener iOnClickListener) {
        circuit.setOnClick(() -> iOnClickListener.onClick());
    }
}

