package com.example.sqlite_database;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
public   EditText uname,email,password;
Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        uname=findViewById(R.id.uname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        MyDBHelper helper=new MyDBHelper(MainActivity.this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add data into database;
                helper.addUser(uname.getText().toString(),email.getText().toString(),password.getText().toString());
                Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
            }
        });
        //fetch data from database;
        ArrayList<Model> arrval=helper.fetchdata();
        for(int i=0;i<arrval.size();i++){
            Log.e("Contact information","id :"+arrval.get(i).id+", name :"+arrval.get(i).uname+", email :"+arrval.get(i).email+", password :"+arrval.get(i).password);
        }

        //update table
        Model model=new Model();
        model.id=2;
        model.password="7461";
        helper.updatetable(model);

        //Delete data
        helper.deletedata(3);
        ArrayList<Model> arrval2=helper.fetchdata();
        for(int i=0;i<arrval2.size();i++){
            Log.e("Contact information","id :"+arrval2.get(i).id+", name :"+arrval2.get(i).uname+", email :"+arrval2.get(i).email+", password :"+arrval2.get(i).password);
        }

    }
}