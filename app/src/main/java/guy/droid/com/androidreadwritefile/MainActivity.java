package guy.droid.com.androidreadwritefile;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
Button button,read;
    EditText editText;
    TextView textView;
    ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        read = (Button)findViewById(R.id.read);
        editText = (EditText)findViewById(R.id.writetofile);
        textView = (TextView)findViewById(R.id.content);
        names = new ArrayList<>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  writeToFile(""+editText.getText());
                try {
                    // writes(""+editText.getText());
                    writesobjs(""+editText.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("--->",readFromFile());
                try {
                   // reads();
                    readsobjs();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //textView.setText(readFromFile());
            }
        });

    }
    private void writeToFile(String data) {
        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(getAssets()+"config.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput(getAssets()+"config.json");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
    public  void writes(String data) throws IOException
    {
        File file = new File(Environment.getExternalStorageDirectory(),"/AppTested/");
        if(file.exists())
        {

        }
        else
        {
            file.mkdir();
        }
        File outputFile = new File(file, "config.txt");
        FileOutputStream os =  new FileOutputStream(outputFile,true); // remove true to disable append mode
        os.write(data.getBytes());
        os.close();

    }
    public void reads() throws IOException
    {
        File file = new File(Environment.getExternalStorageDirectory(),"/AppTested/");
        if(file.exists())
        {

        }
        else
        {
            file.mkdir();
        }
        File outputFile = new File(file, "config.txt");
        try {

            FileInputStream fIn = new FileInputStream(outputFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }
            //txtData.setText(aBuffer);
            myReader.close();
            Toast.makeText(getBaseContext(),
                    ""+aBuffer,
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

    }
    public  void writesobjs(String data) throws IOException
    {
        names.add(data);
        File file = new File(Environment.getExternalStorageDirectory(),"/AppTested/");
        if(file.exists())
        {

        }
        else
        {
            file.mkdir();
        }
        File outputFile = new File(file, "objects.obj");
        FileOutputStream os =  new FileOutputStream(outputFile); // remove true to disable append mode
        os.write(names.toString().getBytes());
       // os.write(data.getBytes());
        os.close();

    }
    public void readsobjs() throws IOException
    {
        File file = new File(Environment.getExternalStorageDirectory(),"/AppTested/");
        if(file.exists())
        {

        }
        else
        {
            file.mkdir();
        }
        File outputFile = new File(file, "objects.obj");
        try {

            FileInputStream fIn = new FileInputStream(outputFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }
            //txtData.setText(aBuffer);
            myReader.close();
            textView.setText(aBuffer);
            String s1=aBuffer;
            String replace = s1.replace("[","");
           // System.out.println(replace);
            String replace1 = replace.replace("]","");
           // System.out.println(replace1);
            ArrayList<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
            //System.out.println(myList.toString());
            for (int i = 0;i<myList.size();i++)
            {
                Log.d("POSITION  "+i+" ",myList.get(i));
            }

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

    }
}
