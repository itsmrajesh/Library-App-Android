package pbl.android.com.libncet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button add_student = (Button) findViewById(R.id.add_student);
        Button add_book = (Button) findViewById(R.id.add_book);
        Button issue_book = (Button) findViewById(R.id.issue_book);

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this,AddStudent.class);
                startActivity(intent);
            }
        });

        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this,AddBook.class);
                startActivity(intent);
            }
        });

        issue_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this,IssueBook.class);
                startActivity(intent);
            }
        });
    }
}
