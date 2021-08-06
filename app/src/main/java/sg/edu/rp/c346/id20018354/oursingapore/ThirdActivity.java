package sg.edu.rp.c346.id20018354.oursingapore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Integer.valueOf;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etName, etDescription, etArea;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etArea = (EditText) findViewById(R.id.etArea);
        ratingBar = findViewById(R.id.ratingbarStars);

        Intent i = getIntent();
        final Island currentIsland = (Island) i.getSerializableExtra("island");

        etID.setText(valueOf(currentIsland.getId()));
        etName.setText(currentIsland.getName());
        etDescription.setText(currentIsland.getDescription());
        etArea.setText(valueOf(currentIsland.getArea()));

        ratingBar.setRating(currentIsland.getStars());
        /*switch (currentSong.getStars()){
            case 5: rb5.setChecked(true);
                    break;
            case 4: rb4.setChecked(true);
                    break;
            case 3: rb3.setChecked(true);
                    break;
            case 2: rb2.setChecked(true);
                    break;
            case 1: rb1.setChecked(true);
        }*/

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentIsland.setName(etName.getText().toString().trim());
                currentIsland.setDescription(etDescription.getText().toString().trim());
                int area = 0;
                try {
                    area = valueOf(etArea.getText().toString().trim());
                } catch (Exception e) {
                    Toast.makeText(ThirdActivity.this, "Invalid area", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentIsland.setArea(area);

                //int selectedRB = rg.getCheckedRadioButtonId();
                //RadioButton rb = (RadioButton) findViewById(selectedRB);
                //currentSong.setStars(Integer.parseInt(rb.getText().toString()));
                currentIsland.setStars((int) ratingBar.getRating());
                int result = dbh.updateIsland(currentIsland);
                if (result > 0) {
                    Toast.makeText(ThirdActivity.this, "Island updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        // Inflate the input.xml layout file
                        LayoutInflater inflater =
                                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View viewDialog = inflater.inflate(R.layout.row, null);

                        // Obtain the UI component in the input.xml layout
                        // It needs to be defined as "final", so that it can used in the onClick() method later

                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                        myBuilder.setView(viewDialog);  // Set the view of the dialog
                        myBuilder.setTitle("Danger");
                        myBuilder.setMessage("Are you sure you want to delete the island\n"+currentIsland.getName());
                        myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper dbh = new DBHelper(ThirdActivity.this);
                                int result = dbh.deleteIsland(currentIsland.getId());
                                if (result > 0) {
                                    Toast.makeText(ThirdActivity.this, "Island deleted", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        myBuilder.setNegativeButton("CANCEL", null);
                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

