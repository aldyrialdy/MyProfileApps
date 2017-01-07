package makers.latihan.myprofileapps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView personalPic;
    Button btnTake, btnEdit;
    TextView txtName, txtWork;
    static final int REQUEST_PROFILE_DETAILS = 11;
    String name="";
    String occupation="";

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personalPic = (ImageView) findViewById(R.id.imgPhoto);
        btnTake = (Button) findViewById(R.id.btnTake);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        txtName = (TextView) findViewById(R.id.txtName);
        txtWork = (TextView) findViewById(R.id.txtWork);


        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EditProfileActivity.class);
                intent.putExtra("name",txtName.getText().toString());
                intent.putExtra("occupation",txtWork.getText().toString());
                startActivityForResult(intent, REQUEST_PROFILE_DETAILS);
            }
        });

        mSharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        String name = mSharedPreferences.getString("name","");
        String occupation = mSharedPreferences.getString("occupation","");
        txtName.setText(name);
        txtWork.setText(occupation);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // intent to camera
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) { // check if camera is available
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE); // running the intent
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) { // check if the request code is from camera and if the result is ok
            Bundle extras = data.getExtras(); // getting data from the camera
            Bitmap imageBitmap = (Bitmap) extras.get("data"); // assign the data, which is a picture to a variable
            personalPic.setImageBitmap(imageBitmap); // applying the picture into an ImageView
        }
        else if (requestCode==REQUEST_PROFILE_DETAILS) {
            name = data.getStringExtra("name");
            occupation = data.getStringExtra("occupation");

            txtName.setText(name);
            txtWork.setText(occupation);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor = mSharedPreferences.edit();
        mEditor.putString("name", txtName.getText().toString());
        mEditor.putString("occupation", txtWork.getText().toString());
        mEditor.apply();
    }
}
