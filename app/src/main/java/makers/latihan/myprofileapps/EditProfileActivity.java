package makers.latihan.myprofileapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static makers.latihan.myprofileapps.MainActivity.REQUEST_POFILE_DETAILS;

public class EditProfileActivity extends AppCompatActivity {

    EditText editName, editOccupation;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editName = (EditText)findViewById(R.id.name);
        editOccupation = (EditText)findViewById(R.id.occupation);
        saveButton = (Button)findViewById(R.id.btnSave);

        Intent intentedit = getIntent();
        editName.setText(intentedit.getStringExtra("name"));
        editOccupation.setText(intentedit.getStringExtra("occupation"));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intentkirim = new Intent();
                intentki
            }
        });

    }
}
