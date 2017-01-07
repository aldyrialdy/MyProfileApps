package makers.latihan.myprofileapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {

    EditText editName, editOccupation;
    Button saveButton;
    static final int REQUEST_PROFILE_DETAILS = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editName = (EditText) findViewById(R.id.name);
        editOccupation = (EditText) findViewById(R.id.occupation);
        saveButton = (Button) findViewById(R.id.btnSave);

        Intent intentedit = getIntent();
        editName.setText(intentedit.getStringExtra("name"));
        editOccupation.setText(intentedit.getStringExtra("occupation"));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentkirim = new Intent();
                intentkirim.putExtra("name", editName.getText().toString());
                intentkirim.putExtra("occupation", editOccupation.getText().toString());
                setResult(REQUEST_PROFILE_DETAILS, intentkirim);
                finish();
            }
        });

    }
}
