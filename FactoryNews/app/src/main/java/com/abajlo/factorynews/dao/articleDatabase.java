package com.abajlo.factorynews.dao;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.abajlo.factorynews.model.Article;
import com.abajlo.factorynews.model.Data;
import com.abajlo.factorynews.presenter.Api;
import com.abajlo.factorynews.retrofit.APIClient;
import com.abajlo.factorynews.utils.Dialogs;

import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;



//Singleton
@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class articleDatabase extends RoomDatabase {

    private static Context context;

    private static List<Article> articles;
    private LiveData<List<Article>> localArticles;

    public abstract ArticleSQL articleSQL();

    private static articleDatabase databaseHandler;

    public static articleDatabase getDatabase(Context context) {
        articleDatabase.context = context;
        if (databaseHandler == null) {
            databaseHandler = Room.databaseBuilder(context.getApplicationContext(), articleDatabase.class, "bazaArticle")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();


        }
        if (databaseHandler != null)
            new PopulateDbAsyncTask(articleDatabase.context, databaseHandler).execute();
        return databaseHandler;
    }

    ;

    public static void deleteInstance() {
        databaseHandler = null;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           // new PopulateDbAsyncTask(articleDatabase.context, databaseHandler).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private final Context context;
        private ArticleSQL articleSQL;


        public PopulateDbAsyncTask(Context context, articleDatabase db) {
            articleSQL = db.articleSQL();
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            articles = articleSQL.localArticles();

            if (articles == null || articles.isEmpty() || articles.get(0).getTimestamp() < System.currentTimeMillis() -  5 * 60 * 1000) {

                Retrofit retrofit = APIClient.getClient(context);

                Api api = retrofit.create(Api.class);

                Call<Data> call = api.getData();

                call.enqueue(new retrofit2.Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        if (!response.isSuccessful()) {
                            return;
                        }
                        Data data = response.body();

                        articles = data.getArticles();
                        long timeStamp = System.currentTimeMillis();
                        articleSQL.clearAll();
                        for (Article article : articles) {

                            article.setTimestamp(timeStamp);
                            articleSQL.addArticle(article);
                        }
                        Log.d("baza updejtana", "Baza updejtana!!!");

                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Intent intent = new Intent(context, Dialogs.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                });
            }

            return null;
        }
    }
}

