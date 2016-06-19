package apps.muskinny.com.smaths;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List_Function extends AppCompatActivity {

    ListView Functions;
    ArrayAdapter<String> list_functions;
    String[] functions = {"Parabola", "Trigonometry", "Function Studying", "Limits", "Derivatives"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_functions);
        Functions = (ListView) findViewById(R.id.list_functions);
        list_functions = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, functions);
        Functions.setAdapter(list_functions);
        Choose_Function();


    }

    public void Choose_Function() {
        Functions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent Second_degree_equation = new Intent(List_Function.this, Parabola.class);
                        startActivity(Second_degree_equation);
                        break;
                    case 1:
                        Intent Function_Studying = new Intent(List_Function.this, Trigonometry.class);
                        startActivity(Function_Studying);
                        break;
                }

            }
        });
    }
}
