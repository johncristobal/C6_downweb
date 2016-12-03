package vera.moon.com.c6_downweb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    DownloadTask task;

    public ImageView image;

    //Stirng...param que recibe
    //Void ...metodo para actualiar views
    //String...valor a regresar
    public class DownloadTask extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {

            try{

                URL url = new URL(strings[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream in = connection.getInputStream();

                //get the bitmap...convert data to image
                Bitmap bitmap = BitmapFactory.decodeStream(in);

                return  bitmap;

            }catch(Exception e){e.printStackTrace();}

            return null;
        }

        //Really can be access in anywhere in the app?
        /*@Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection connection = null;

            try{

                url = new URL(urls[0]);

                connection = (HttpURLConnection) url.openConnection();

                //input of data
                InputStream in = connection.getInputStream();

                //read data
                InputStreamReader reead = new InputStreamReader(in);
                //read characters from reead
                int data = reead.read();
                while(data != -1){

                    char caracter = (char)data;

                    result += caracter;

                    data = reead.read();
                }

                return result;


            }catch(Exception e){e.printStackTrace();}

            return "Failed to catch web";
        }*/


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.imageView);

        task = new DownloadTask();
        //This string is store as array in Strings
        /*Bitmap res = null;
        try {
            res = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            image.setImageBitmap(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //Log.w("return",res.toString());

    }

    public void imagen(View v){
        Bitmap res = null;
        try {
            res = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            image.setImageBitmap(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
