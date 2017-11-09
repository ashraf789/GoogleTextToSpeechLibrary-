package com.example.syed.texttospeechabcd;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    final private String[] abcd = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    final private String[] abcdExample = {"Apple","Ball","Cat","Dog","Eat","Friend","Game","Hello","In","Jump","King","Love","Mom","Nice","Orange",
            "Pick","Queen","Room","Ship","Thanks","Uncle","Value","Winter","X-Man","Yes","Zoo"};
    TextToSpeech textToSpeechObj;
    GridView viewGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewGrid = (GridView) findViewById(R.id.grid_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,abcd);
        viewGrid.setAdapter(adapter);


        textToSpeechObj = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    textToSpeechObj.setLanguage(Locale.US);
                }
            }
        });

        viewGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textToSpeechObj.speak(abcd[position],TextToSpeech.QUEUE_FLUSH,null);
                Toast.makeText(MainActivity.this,abcd[position],Toast.LENGTH_SHORT).show();
            }
        });

        viewGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                textToSpeechObj.speak(abcd[position]+"For"+abcdExample[position],TextToSpeech.QUEUE_FLUSH,null);
                Toast.makeText(MainActivity.this,abcdExample[position],Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        if (textToSpeechObj != null){
            textToSpeechObj.stop();
            textToSpeechObj.shutdown();
        }
        super.onPause();
    }
}
