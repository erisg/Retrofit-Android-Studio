package bog.skate.eris.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.list_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        api api = retrofit.create(api.class);
        Call<List<hero>> call = api.getheroes();

        call.enqueue(new Callback<List<hero>>() {
            @Override
            public void onResponse(Call<List<hero>> call, Response<List<hero>> response) {
                List<hero> heroes = response.body();

                String[] heroRealname = new String[heroes.size()];

                for (int i = 0; i  < heroes.size(); i++) {
                    heroRealname[i] = heroes.get(i).getRealname();
                }

                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                heroRealname
                        )
                );

            }

            @Override
            public void onFailure(Call<List<hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
