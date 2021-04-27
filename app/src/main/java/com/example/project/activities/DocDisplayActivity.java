package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.model.registeredDocs;
import com.example.project.retrofitUtil.APIClient;
import com.example.project.retrofitUtil.Adapter;
import com.example.project.retrofitUtil.ApiInterface;
//import com.example.project.retrofitUtil.myAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<registeredDocs> regDocs;
    private Adapter adapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;
    private Adapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_display);

        setOnClickListener();
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


       fetchUsers("");
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(this,docProfileActivity.class);
            intent.putExtra("name",regDocs.get(position).getName());
            startActivity(intent);
        };
    }

    public void fetchUsers(String key){
        //Toast.makeText(DocDisplayActivity.this,"keep tring",Toast.LENGTH_SHORT).show();
        apiInterface = APIClient.getApiClient().create(ApiInterface.class);
        Call<List<registeredDocs>>call = apiInterface.getUser(key);
            call.enqueue(new Callback<List<registeredDocs>>() {
                @Override
                public void onResponse(Call<List<registeredDocs>> call, Response<List<registeredDocs>> response) {
                    progressBar.setVisibility(View.GONE);
                    regDocs = response.body();
                    adapter = new Adapter(regDocs, DocDisplayActivity.this,listener);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<registeredDocs>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(DocDisplayActivity.this,"Error on" + t.toString(),Toast.LENGTH_SHORT).show();

                }
            });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchUsers(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchUsers(newText);
                return false;
            }
        });
        return true;
    }
}
