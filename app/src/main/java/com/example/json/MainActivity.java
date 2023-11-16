package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import com.example.json.R;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<User> list;
    ListviewAdapter listviewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();
        new ReadJSON().execute("https://dummyjson.com/users");
    }
    private class ReadJSON extends AsyncTask<String, Void, String> {
        StringBuilder content = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line ="";
                while ((line = bufferedReader.readLine())!=null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("users");
                for (int i=0; i<array.length();i++)
                {
                    JSONObject objectUser = array.getJSONObject(i);
                    String image = objectUser.getString("image");
                    String firstname = objectUser.getString("firstName");
                    String lastname = objectUser.getString("lastName");
                    String gender = objectUser.getString("gender");
                    int age = objectUser.getInt("age");
                    String phone = objectUser.getString("phone");
                    list.add(new User(firstname+" "+lastname, gender, age, phone, image));
                }
                listviewAdapter = new ListviewAdapter(getApplicationContext(), R.layout.custom_listview, list);
                listView.setAdapter(listviewAdapter);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            super.onPostExecute(s);
        }
    }
}