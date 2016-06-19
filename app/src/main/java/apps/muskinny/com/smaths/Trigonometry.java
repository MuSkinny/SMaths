package apps.muskinny.com.smaths;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Trigonometry extends AppCompatActivity {

    EditText Function_View;
    TextView ResultView;
    Button Cos, Sen, Tan, Canc;
    double cosy, seny;
    double cosx = -5.0;
    double senx = -5.0;
    LineGraphSeries<DataPoint> Cosx, Senx;
    double a, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trigonometry);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Init();


    }

    public void Init() {
        Cos = (Button) findViewById(R.id.ButtonCos);
        Function_View = (EditText) findViewById(R.id.FunctionView);
        Sen = (Button) findViewById(R.id.ButtonSen);
        ResultView = (TextView) findViewById(R.id.Result_Trigonometry);
        ResultView.setTextColor(Color.BLACK);
        Tan = (Button) findViewById(R.id.ButtonTan);
        Canc = (Button) findViewById(R.id.buttonDel);

        final GraphView trigograph = (GraphView) findViewById(R.id.Trigo_graphic);
        Cosx = new LineGraphSeries<DataPoint>();
        Senx = new LineGraphSeries<DataPoint>();

        // set manual X bounds
        trigograph.getViewport().setXAxisBoundsManual(true);
        trigograph.getViewport().setMinX(-5.0);
        trigograph.getViewport().setMaxX(5.0);

        // set manual Y bounds
        trigograph.getViewport().setYAxisBoundsManual(true);
        trigograph.getViewport().setMinY(-2.0);
        trigograph.getViewport().setMaxY(2.0);
        trigograph.getViewport().setScrollable(true);
        trigograph.getViewport().setScalable(true);
        trigograph.setTitleTextSize(60);


        Tan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadValue();
                calcTan();
            }
        });

        Cos.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadValue();
                calcCos();
                DrawCos();
                trigograph.addSeries(Cosx);
                trigograph.setTitle("Cos(x)");
            }
        });

        Sen.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadValue();
                calcSen();
                DrawSen();
                trigograph.addSeries(Senx);
                trigograph.setTitle("Sen(X)");
            }
        });

        Canc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Function_View.setText("");
                a = 0;
                ShowTXT("");
                result = 0;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    public void ReadValue() {
        if (Function_View.getText().toString().equals(""))
            a = 0;
        else
            a = Double.valueOf(Function_View.getText().toString());
    }

    public void calcCos() {
        a = Math.toRadians(a);
        result = Math.cos(a);
        ShowTXT("Cos(" + String.format("%.2f", Math.toDegrees(a)) + ") = " + String.format("%.4f", result));
    }

    public void calcSen() {
        a = Math.toRadians(a);
        result = Math.sin(a);
        ShowTXT("Sen(" + String.format("%.2f", Math.toDegrees(a)) + ") = " + String.format("%.4f", result));

    }

    public void calcTan() {
        a = Math.toRadians(a);
        result = Math.tan(a);
        ShowTXT("Tan(" + String.format("%.2f", Math.toDegrees(a)) + ") = " + String.format("%.4f", result));
    }


    public void ShowTXT(String message) {
        ResultView.setText(message);
    }

    public void DrawSen() {
        for (int i = 0; i < 150; i++) {
            senx = senx + 0.1;
            //y = ((-1*Math.pow(x,2)) + 5*x +-1);
            seny = Math.sin(senx);
            Senx.appendData(new DataPoint(senx, seny), true, 150);
            Senx.setColor(Color.RED);
        }

    }

    public void DrawCos() {
        for (int i = 0; i < 150; i++) {
            cosx = cosx + 0.1;
            //y = ((-1*Math.pow(x,2)) + 5*x +-1);
            cosy = Math.cos(cosx);
            Cosx.appendData(new DataPoint(cosx, cosy), true, 150);
            Cosx.setColor(Color.BLUE);
        }
    }

}
