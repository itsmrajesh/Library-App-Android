package pbl.android.com.libncet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IssueBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_book);

        final SqlDB sqlDB = new SqlDB(this,"donothing");

        final EditText book_title = (EditText) findViewById(R.id.title_edit);
        final EditText book_id = (EditText) findViewById(R.id.id_edit);
        final EditText USN = (EditText) findViewById(R.id.USN_edit);
        final EditText Username = (EditText) findViewById(R.id.username_edit);
        final EditText DOI = (EditText) findViewById(R.id.DOI_edit);
        final EditText DOR = (EditText) findViewById(R.id.DOR_edit);

        Button add_button = (Button) findViewById(R.id.add_button);
        Button cancel_button = (Button) findViewById(R.id.cancel_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !book_title.getText().toString().matches("")
                        && !book_id.getText().toString().matches("")
                        && !USN.getText().toString().matches("")
                        && !Username.getText().toString().matches("")
                        && !DOI.getText().toString().matches("")
                        && !DOR.getText().toString().matches("")){
                    if (sqlDB.check_issue_count(USN.getText().toString())) {
                        long res = sqlDB.Insertvalue_Library(book_title.getText().toString(), Integer.parseInt(book_id.getText().toString()),
                                USN.getText().toString(), Username.getText().toString(), DOI.getText().toString(), DOR.getText().toString());

                        if (res == 0) {
                            Toast.makeText(IssueBook.this, "UNSUCCESSFULL", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(IssueBook.this, "SUCCESSFULL", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(IssueBook.this, Home_page.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(IssueBook.this, "YOU HAVE TAKEN 4 BOOKS", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(IssueBook.this, "FILL ALL THE DETAILS", Toast.LENGTH_SHORT).show();
                }
                }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IssueBook.this,Home_page.class);
                startActivity(intent);
            }
        });
    }
}
