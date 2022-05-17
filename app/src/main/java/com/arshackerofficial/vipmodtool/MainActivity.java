package com.arshackerofficial.vipmodtool;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

public class MainActivity extends AppCompatActivity {

    EditText key;
    String dbkey;
    String dbcode;
    String dbmsg;
    String dbdaysleft;
    String dbvalidtill;
    String dbtoken;
    CheckBox remPass;
    ImageButton showpass;
    AlertDialog.Builder builder;
    Boolean passShowed;
    ImageButton paste;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        //Some Basic Properties

        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 101);
        }



        passShowed = false;
        paste = findViewById(R.id.paste);
        login = findViewById(R.id.login);
        key = findViewById(R.id.key);
        remPass = findViewById(R.id.rempass);
        showpass = findViewById(R.id.showpass);
        builder = new AlertDialog.Builder(MainActivity.this);



        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Verifying login details...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        SharedPreferences sharedPrefs = getSharedPreferences("VipModDataConfig", MODE_PRIVATE);




        if(sharedPrefs.contains("key")){
            String shkey = sharedPrefs.getString("key", "");
            key.setText(shkey);
            remPass.setChecked(true);
        }

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passShowed){
                    key.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passShowed = false;
                } else{
                    key.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passShowed = true;
                }
            }
        });

        paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String pasteData = "";
                if (!(clipboard.hasPrimaryClip())) {
                } else if (!(clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {
                } else {
                    ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                    pasteData = item.getText().toString();
                }
                key.setText(pasteData);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                progressDialog.setCancelable(false);


                if(remPass.isChecked()){
                    // Storing data into SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("VipModDataConfig",MODE_PRIVATE);

                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    myEdit.putString("key", key.getText().toString());

                    myEdit.commit();
                }


                String kkkeeeyyy = key.getText().toString();
                String uuuiiisss = getUniqueId(MainActivity.this);
                String url = "https://vip-mod-data.000webhostapp.com/login.php?key=" + kkkeeeyyy + "&uid=" + uuuiiisss;



                String json = getContents(url);

                try {
                    JSONObject obj = new JSONObject(json);
                    dbmsg = obj.getString("msg");
                    dbcode = obj.getString("code");
                    dbkey = obj.getString("Username");
                    dbdaysleft = obj.getString("Valid");
                    dbvalidtill =obj.getString("EndDate");
                    dbtoken = obj.getString("token");

                } catch (JSONException e) {
                    e.printStackTrace();
                }





                if(dbcode.equals("0")){
                    progressDialog.dismiss();
                   builder.setMessage(dbmsg).setTitle("Buy a new key!").setCancelable(false).setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try
                                    {
                                        MainActivity.this.getPackageManager().getPackageInfo("org.telegram.messenger", PackageManager.GET_ACTIVITIES);
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=A_MODER")));
                                        finish();
                                    }
                                    catch (PackageManager.NameNotFoundException e)
                                    {
                                        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://t.me/A_MODER")));
                                        finish();
                                    }
                                }
                            });
                    builder.show();
                } else if(dbcode.equals("1")){
                    progressDialog.dismiss();
                    builder.setMessage(dbmsg + "\nDays Remaining: "+dbdaysleft+"\nValid Till: "+ dbvalidtill).setTitle("Login Success").setCancelable(false).setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(MainActivity.this, ModActivity.class).putExtra("token", dbtoken).putExtra("key",dbkey));
                        }
                    });
                    builder.show();
                }



            }

        });
    }


    public static String getContents(String url) {
        String contents ="";

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }

        try {
            URLConnection conn = new URL(url).openConnection();

            InputStream in = conn.getInputStream();
            contents = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.v("MALFORMED URL EXCEPTION",e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(e.getMessage(), e.getMessage());
            e.printStackTrace();
        }

        return contents;
    }

    private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

        BufferedReader reader = new BufferedReader(new
                InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private String getUniqueId(Context ctx) {
        String key = (getDeviceName() + Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID) + Build.HARDWARE).replace(" ", "");
        UUID uniqueKey = UUID.nameUUIDFromBytes(key.getBytes());
        return uniqueKey.toString().replace("-", "");
    }

    private String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }

}