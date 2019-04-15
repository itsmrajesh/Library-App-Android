package pbl.android.com.libncet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        final SqlDB sqlDB = new SqlDB(this,"donothing");

        final EditText bookid = (EditText) findViewById(R.id.id_edit);
        final EditText bookname = (EditText) findViewById(R.id.title_edit);
        final EditText bookauthor = (EditText) findViewById(R.id.author_edit);

        Button add_button = (Button) findViewById(R.id.add_button);
        Button cancel_button = (Button) findViewById(R.id.cancel_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bookid.getText().toString().matches("")
                && !bookname.getText().toString().matches("")
                && !bookauthor.getText().toString().matches("")){

                   long res = sqlDB.Insertvalues_BOOK(Integer.parseInt(bookid.getText().toString()),bookname.getText().toString()
                            ,bookauthor.getText().toString());

                    if (res == 0) {
                        Toast.makeText(AddBook.this, "UNSUCCESSFULL", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddBook.this, "SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent  = new Intent(AddBook.this,Home_page.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddBook.this, "FILL ALL THE DETAILS", Toast.LENGTH_SHORT).show();
                }


                }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBook.this,Home_page.class);
                startActivity(intent);
            }
        });
    }
}
