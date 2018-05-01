package be.lsinf1225gr12.minipoll.minipollapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void profile(View v)
    {

    }

    public void friends(View v)
    {

    }

    public void create(View v)
    {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void choice(View v)
    {

    }

    public void poll(View v)
    {

    }

    public void MCQ(View v)
    {

    }
}