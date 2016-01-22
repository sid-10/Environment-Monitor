package com.example.siddharth.airpollution;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.EntityUtils;

import java.util.ArrayList;
/**
 * Created by Siddharth and Mukesh on 17/1/16.
 */
public class ServerRequests {
        ProgressDialog progressDialog;
        public static final int CONNECTION_TIMEOUT= 1000*15;
        public static final String SERVER_ADDRESS= "http://helo.host56.com/";

        public ServerRequests(Context context){
            progressDialog =new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Processing");
            progressDialog.setMessage("Logging in...");
        }
        public void storeUserDataInBackground(User user, GetUserCallback userCallback){
            progressDialog.show();
            new StoreUserDataAsyncTask(user, userCallback).execute();
        }
        public void fetchUserDataInBackground(User user, GetUserCallback callBack){
            progressDialog.show();
            new fetchUserDataAsyncTask(user, callBack).execute();
        }

        public class  StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>{
        User user;
        GetUserCallback userCallback;

            public StoreUserDataAsyncTask(User user, GetUserCallback userCallback){
                this.user=user;
                this.userCallback=userCallback;
            }
            @Override
            protected Void doInBackground(Void... params) {
                ArrayList<NameValuePair> dataToSend=new ArrayList<>();
                dataToSend.add(new BasicNameValuePair("name", user.name));
                dataToSend.add(new BasicNameValuePair("age", user.age + ""));
                dataToSend.add(new BasicNameValuePair("username", user.username));
                dataToSend.add(new BasicNameValuePair("password", user.password));

                HttpParams httpRequestParams=new BasicHttpParams();
                cz.msebera.android.httpclient.params.HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
                cz.msebera.android.httpclient.params.HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

                HttpClient client =new DefaultHttpClient();
                HttpPost post=new HttpPost(SERVER_ADDRESS + "Register.php");
                try{
                    post.setEntity(new UrlEncodedFormEntity(dataToSend));
                    client.execute(post);
                }catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
               progressDialog.dismiss();
                userCallback.done(null);

                super.onPostExecute(aVoid);
            }
    }

    public class  fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend=new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams=new BasicHttpParams();
            cz.msebera.android.httpclient.params.HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            cz.msebera.android.httpclient.params.HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client =new DefaultHttpClient();
            HttpPost post=new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            User returnedUser=null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse= client.execute(post);

                HttpEntity entity=httpResponse.getEntity();
                String result= EntityUtils.toString(entity);
                JSONObject jObject =new JSONObject(result);
                if(jObject.length()==0){
                    returnedUser=null;

                }else{
                    String name=jObject.getString("name");
                    int age = jObject.getInt("age");
                    returnedUser=new User(name, age, user.username, user.password);
                }


            }catch(Exception e){
                e.printStackTrace();
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser){
            progressDialog.dismiss();
            userCallback.done(returnedUser);

            super.onPostExecute(returnedUser);
        }
    }
}
