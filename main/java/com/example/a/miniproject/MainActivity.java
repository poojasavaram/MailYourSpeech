package com.example.a.miniproject;

import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    String mod=" ",str=" ";
    public void onSpeechtoText(View view)
    {
        Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
        if(i.resolveActivity(getPackageManager())!=null)
            startActivityForResult(i,10);
        else
            Toast.makeText(this,"Your Device doesn't support ",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&data!=null)
        {
            ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            TextView tv=(TextView)findViewById(R.id.txt1);
            tv.setText(result.get(0));
            str=result.get(0);
        }
    }
    public void append(View view)
    {
        mod=mod+"\n"+str;
        TextView tv2=(TextView)findViewById(R.id.txt2);
        tv2.setText(mod.toString());
    }
    public void reset(View view)
    {
        TextView tv2=(TextView)findViewById(R.id.txt2);
        tv2.setText(" ");
        TextView tv3=(TextView)findViewById(R.id.txt1);
        tv3.setText(" ");
        mod=" ";

    }
    public void send(View view)
    {
        mod=mod.toUpperCase();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Pooja has txted you!!");
        intent.putExtra(Intent.EXTRA_TEXT,mod);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
