package com.example.laboratorio10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private ArrayList<String> titulos = new ArrayList<String>();
    private ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adaptador= new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, titulos);
        lista = findViewById(R.id.lista);
        lista.setAdapter(adaptador);

        Button btn1 = (Button) findViewById(R.id.get_post);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosts();
            }
        });

        Button btn2 = (Button) findViewById(R.id.get_post_by_id);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed = (EditText) findViewById(R.id.id);
                String id ="10";
                if (!ed.getText().toString().equals("")){
                    id = ed.getText().toString();
                }

                getPostByID(id);
            }
        });
    }

    public void getPosts(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService post = retrofit.create(PostService.class);
        Call<List<Post>> call = post.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                for (Post post : response.body()){
                    System.out.println(post.getTitle());
                    titulos.add(post.getTitle());
                }
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    public void getPostByID(String id){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService post = retrofit.create(PostService.class);
        System.out.println(id);
        Call<List<Post>> call = post.getPostById(id);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                for (Post post : response.body()){
                    System.out.println(post.getTitle());
                    titulos.add(post.getTitle());
                }
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}