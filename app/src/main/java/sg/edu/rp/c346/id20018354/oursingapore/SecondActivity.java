package sg.edu.rp.c346.id20018354.oursingapore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Island> islandList;
    //ArrayAdapter<Island> adapter;
    Button btn5Stars;

//    ArrayList<String> areas;
//    Spinner spinner;
//    ArrayAdapter<String> spinnerAdapter;
    CustomAdapter adapter;
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        islandList.clear();
        islandList.addAll(dbh.getAllIslands());
        adapter.notifyDataSetChanged();

//        areas.clear();
//        areas.addAll(dbh.getArea());
//        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_second));

        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);
//        spinner = (Spinner) this.findViewById(R.id.spinnerArea);

        DBHelper dbh = new DBHelper(this);
        islandList = dbh.getAllIslands();
//        areas = dbh.getArea();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, islandList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("island", islandList.get(position));
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                islandList.clear();
                islandList.addAll(dbh.getAllIslandsByStars(5));
                adapter.notifyDataSetChanged();
            }
        });

//        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, areas);
//        spinner.setAdapter(spinnerAdapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                DBHelper dbh = new DBHelper(SecondActivity.this);
//                islandList.clear();
//                islandList.addAll(dbh.getAllIslandsByArea(Integer.valueOf(areas.get(position))));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }
}
