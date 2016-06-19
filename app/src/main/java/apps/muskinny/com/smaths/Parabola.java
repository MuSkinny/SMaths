package apps.muskinny.com.smaths;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class Parabola extends AppCompatActivity {

    double a, b, c;
    EditText A, B, C;
    Button Calc, Theory, Graph, Canc;
    TextView Equation_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parabola);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Init();
    }

    public void Init() {
        A = (EditText) findViewById(R.id.factor_a);
        B = (EditText) findViewById(R.id.factor_b);
        C = (EditText) findViewById(R.id.factor_c);

        Calc = (Button) findViewById(R.id.button_calculator);
        Theory = (Button) findViewById(R.id.button_how);
        Graph = (Button)findViewById(R.id.button_graph);
        Canc = (Button)findViewById(R.id.button_canc);

        Equation_View = (TextView)findViewById(R.id.equationView);

        Calc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                read();
                openResults();
            }
        });

        Graph.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                read();
                checkForGraph();
            }
        });

        Theory.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheroy();
            }
        });

        Canc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                A.setText("");
                B.setText("");
                C.setText("");

                a = 0;
                b = 0;
                c = 0;
            }
        });

    }
public void read() {
    if (A.getText().toString().equals(""))
        a = 0;
    else
        a = Double.valueOf(A.getText().toString());
    if (B.getText().toString().equals(""))
        b = 0;
    else
        b = Double.valueOf(B.getText().toString());
    if (C.getText().toString().equals(""))
        c = 0;
    else
        c = Double.valueOf(C.getText().toString());
   }


    private void openResults() {
        Intent ResultPage = new Intent(Parabola.this, Solutions_ParabolaEq.class);
        ResultPage.putExtra("a", a);
        ResultPage.putExtra("b", b);
        ResultPage.putExtra("c", c);
        startActivity(ResultPage);
    }

    private void openGraphic() {
        Intent toGraphic = new Intent(Parabola.this, Graphic_Parabola.class);
        toGraphic.putExtra("a", a);
        toGraphic.putExtra("b", b);
        toGraphic.putExtra("c", c);
        startActivity(toGraphic);
    }

    private void openTheroy() {
        Intent toTheory = new Intent(Parabola.this, Theory_ParabolaEq.class);
        startActivity(toTheory);
    }

    public void checkForGraph() {
        if (a == 0 && b == 0 && c ==0) {
        Show("The graphic can't be drown without correct values");
        } else {
            openGraphic();
        }
    }

    public void Show(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    public static class Solutions_ParabolaEq extends AppCompatActivity {

        TextView deltaView, viewX1, viewX2, solView, X1Num, X1Denom, X2Num, X2Denom ;
        View Line, Line2;
        public String NUM;
        public String Denom;
        double a, b, c, delta;
        double x1 = 0, x2 = 0, x = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.solutions_parabola);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            StoreValues();

        }

        public void StoreValues() {
            deltaView = (TextView) findViewById(R.id.Delta_View);
            viewX1 = (TextView) findViewById(R.id.x1View);
            viewX2 = (TextView) findViewById(R.id.x2View);
            solView = (TextView)findViewById(R.id.solutions_text);
            X1Num = (TextView)findViewById(R.id.Nominator);
            Line = (View)findViewById(R.id.fractionLine);
            X1Denom = (TextView)findViewById(R.id.Denominator);
            X2Num = (TextView)findViewById(R.id.Nominator2);
            Line2 = (View)findViewById(R.id.fractionLine2);
            X2Denom = (TextView)findViewById(R.id.Denominator2);


            Bundle factorPassed = this.getIntent().getExtras();

            a = factorPassed.getDouble("a");
            b = factorPassed.getDouble("b");
            c = factorPassed.getDouble("c");

            CalcSolutions();
            Rational xf1 = new Rational(x1);
            X1Num.setText(NUM);
            X1Denom.setText(Denom);

            Rational xf2 = new Rational(x2);
            X2Num.setText(NUM);
            X2Denom.setText(Denom);
            /*viewX1.setText("x1 =  " + xf1);
            viewX2.setText("x2 =  " + xf2);*/


        }

        public void CalcSolutions() {
            if (a != 0 && (b!=0 || c!=0)) {
                delta = b * b - 4 * a * c;
                if (delta > 0) {
                    deltaView.setText("Delta = " +delta);

                    x1 = (-b + Math.sqrt(delta)) / (2 * a);
                    x2 = (-b - Math.sqrt(delta)) / (2 * a);
                    viewX1.setText("x1 =  " + String.format("%.2f" , x1));
                    viewX2.setText("x2 =  " + String.format("%.2f" , x2));
                } else {
                    if (delta == 0) {
                        x = -b / (2 * a);
                        deltaView.setText("delta = 0, just one solution");
                        viewX1.setText("the solution is " + String.format("%.2f" , x));
                    }
                } if(delta<0) {
                    deltaView.setText("delta = " + delta);
                    solView.setText("there are no solutions");
                }
            } else if (a == 0 && b!=0 && c!=0){
                deltaView.setText("first degree equation");
                x = (-c)/b;
                viewX1.setText("the solution is " + String.format("%.2f" , x));
            } else
            if((b!=0 || a!=0) && c==0) {
                deltaView.setText("false equation");
                viewX1.setText("the solution is: 0");
            }
            else deltaView.setText("No corrected values entered");
        }

        public  class Rational {

            private int num, denom;

            public Rational(double d) {
                String s = String.valueOf(d);
                int digitsDec = s.length() - 1 - s.indexOf('.');
                int denom = 1;
                for (int i = 0; i < digitsDec; i++) {
                    d *= 10;
                    denom *= 10;
                }

                int num = (int) Math.round(d);
                this.num = num;
                this.denom = denom;
                NUM = String.valueOf(num);
                Denom = String.valueOf(denom);
            }

            public Rational(int num, int denom) {
                this.num = num;
                this.denom = denom;
            }

            public String toString() {
                return String.valueOf(num)  + "/"  + String.valueOf(denom);

            }
        }

        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if (id == android.R.id.home) {
                finish();
            }
     return true;
        }

    }

    public static class Theory_ParabolaEq extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.theory_parabola);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if (id == android.R.id.home) {
                finish();
            }
            return true;
        }

    }

    public static class Graphic_Parabola extends AppCompatActivity {

        LineGraphSeries<DataPoint> series;

        double y, aX;
        double x = -5.0;
        double aY = - 15.0;
        double a, b, c;
        double aa, bb;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.graphic_parabola);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Bundle valuesPassed = this.getIntent().getExtras();
            a = valuesPassed.getDouble("a");
            b = valuesPassed.getDouble("b");
            c = valuesPassed.getDouble("c");

            aa = (-b)/(2*a);
            bb = (-(b*b - (a*c*4)))/(4*a);

            Draw();

        }

        public void Draw() {
            GraphView graph = (GraphView) findViewById(R.id.graph);
            series = new LineGraphSeries<DataPoint>();
            for (int i = 0; i < 500; i++) {
                x = x + 0.1;
                //y = ((-1*Math.pow(x,2)) + 5*x +-1);
                y = ((a) * Math.pow(x, 2)) + b * x + c;
                //y= Math.sin(x);
                series.appendData(new DataPoint(x, y), true, 500);
                series.setColor(Color.RED);

            }

            PointsGraphSeries<DataPoint> vert = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(aa, bb)
            });
            vert.setShape(PointsGraphSeries.Shape.POINT);
            vert.setColor(Color.BLACK);
            vert.setSize(10);
            vert.setTitle("V");



            LineGraphSeries<DataPoint> ax = new LineGraphSeries<>();
            for (int i = 0; i < 500; i++) {
                aY = aY + 0.1;
                aX = (-b)/(2*a);
                ax.appendData(new DataPoint(aX, aY), true, 500);
                ax.setColor(Color.BLUE);
                ax.setTitle("Ax");
            }



            graph.addSeries(series);
            graph.addSeries(vert);
            graph.addSeries(ax);

            // set manual X bounds
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(-5.0);
            graph.getViewport().setMaxX(5.0);

            // set manual Y bounds
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(-15.0);
            graph.getViewport().setMaxY(15.0);

            graph.getViewport().setScrollable(true);
            graph.getViewport().setScalable(true);
            graph.setTitleTextSize(60);

        }

        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if (id == android.R.id.home) {
                finish();
            }
            return true;
        }
        }
}


