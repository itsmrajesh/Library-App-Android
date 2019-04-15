package pbl.android.com.libncet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SqlDB sqlDB = new SqlDB(this);

        Button ok_button = (Button) findViewById(R.id.ok_button);

        final EditText username_edit = (EditText) findViewById(R.id.username_edit);
        final EditText password_edit = (EditText) findViewById(R.id.password_edit);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = sqlDB.getWritableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + sqlDB.LOGIN_TABLE_NAME,null);

                if (cursor.getCount() == 0){
                    Log.i("mytag", "Get_LOGIN_data: NO ENTRIES");
                }else {
                    while (cursor.moveToNext()){

                        String username = cursor.getString(0);
                         String password = cursor.getString(1);

                         String usernameEdit = username_edit.getText().toString();
                         String passwordEdit =  password_edit.getText().toString();

                        if ( username.equals(usernameEdit) && password.equals(passwordEdit)){
                                Intent intent = new Intent(MainActivity.this,Home_page.class);
                                startActivity(intent);
                                break;
                            }else {
                            Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
            }
        });
    }
}
