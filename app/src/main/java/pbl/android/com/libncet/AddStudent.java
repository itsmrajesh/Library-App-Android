package pbl.android.com.libncet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        final SqlDB sqlDB = new SqlDB(this,"donothing");

        final EditText username = (EditText) findViewById(R.id.username_edit);
        final EditText usn = (EditText) findViewById(R.id.USN_edit);
        final EditText age = (EditText) findViewById(R.id.Age_edit);
        final EditText sem = (EditText) findViewById(R.id.Semester_edit);
        final EditText dept = (EditText) findViewById(R.id.Dept_edit);

        Button add_button = (Button) findViewById(R.id.add_button);
        Button cancel_button = (Button) findViewById(R.id.cancel_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!username.getText().toString().matches("")
                        && !usn.getText().toString().matches("")
                        && !age.getText().toString().matches("")
                        && !sem.getText().toString().matches("")
                        && !dept.getText().toString().matches("")) {
                    long res = sqlDB.Insertvalues_STUDENT(username.getText().toString(), usn.getText().toString(), Integer.parseInt(age.getText().toString()),
                            Integer.parseInt(sem.getText().toString()), dept.getText().toString());

                    if (res == 0) {
                            Toast.makeText(AddStudent.this, "UNSUCCESSFULL", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddStudent.this, "SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    }
                    goBack_Homepage();
                    } else {
                        Toast.makeText(AddStudent.this, "FILL ALL THE DETAILS", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack_Homepage();
            }
        });
    }

    public void goBack_Homepage(){
        Intent intent  = new Intent(AddStudent.this,Home_page.class);
        startActivity(intent);
    }

}
