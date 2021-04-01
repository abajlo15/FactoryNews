package com.abajlo.factorynews.retrofit;

import android.content.Context;
import android.content.Intent;
import com.abajlo.factorynews.utils.Dialogs;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkInterceptor  implements Interceptor {

   private Context context;

    public NetworkInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        response.code();
        if(response.code() != 200) {
            if (context != null) {
                Intent intent = new Intent(context, Dialogs.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }

        return response;
    }



}
