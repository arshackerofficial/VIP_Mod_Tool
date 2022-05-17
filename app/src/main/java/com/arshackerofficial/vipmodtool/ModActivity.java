package com.arshackerofficial.vipmodtool;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class ModActivity extends AppCompatActivity {
    private Button mod1,mod2,mod3,modPak,modData,stop,start;
    private RadioButton gl,kr,vng,tw,lite,bgmi;
    private Context context;
    private String preKey,preToken,extLocName,uriFor1,uriFor2 = "";
    private TextView versionName,modtype;
    private boolean fromStorage = false;
    private Intent i = new Intent();
    private androidx.documentfile.provider.DocumentFile dFile;
    private static final int new_folder = 43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mod);

        if(!checkPermission(pathToRealUri("/storage/emulated/0/Android/"))){
            askPermission(pathToUri("/storage/emulated/0/Android/"));
        }


        preKey = getIntent().getStringExtra("key");
        preToken = getIntent().getStringExtra("token");

        gl = findViewById(R.id.gl);
        kr = findViewById(R.id.kr);
        vng = findViewById(R.id.vng);
        tw = findViewById(R.id.tw);
        lite = findViewById(R.id.lite);
        bgmi = findViewById(R.id.bgmi);
        mod1 = findViewById(R.id.mod1);
        mod2 = findViewById(R.id.mod2);
        mod3 = findViewById(R.id.mod3);
        modPak = findViewById(R.id.modPak);
        modData = findViewById(R.id.modData);
        stop = findViewById(R.id.stop);
        start = findViewById(R.id.start);
        versionName = findViewById(R.id.version);
        modtype = findViewById(R.id.modname);


        //Select Game

        gl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    kr.setChecked(false);
                    vng.setChecked(false);
                    tw.setChecked(false);
                    lite.setChecked(false);
                    bgmi.setChecked(false);

                    versionName.setText("Version: Global");

                    extLocName = "com.tencent.ig";
                }

                if (!gl.isEnabled()) {
                    Toast.makeText(ModActivity.this, "Mod Already Applied on " + versionName.getText().toString() + "\n So firstly stop MOD to continue!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        kr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gl.setChecked(false);
                    vng.setChecked(false);
                    tw.setChecked(false);
                    lite.setChecked(false);
                    bgmi.setChecked(false);

                    versionName.setText("Version: Korea");

                    extLocName = "com.pubg.krmobile";
                }

                if (!kr.isEnabled()) {
                    Toast.makeText(ModActivity.this, "Mod Already Applied on " + versionName.getText().toString() + "\n So firstly stop MOD to continue!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gl.setChecked(false);
                    kr.setChecked(false);
                    tw.setChecked(false);
                    lite.setChecked(false);
                    bgmi.setChecked(false);

                    versionName.setText("Version: Vietnam");

                    extLocName = "com.vng.pubgmobile";
                }

                if (!vng.isEnabled()) {
                    Toast.makeText(ModActivity.this, "Mod Already Applied on " + versionName.getText().toString() + "\n So firstly stop MOD to continue!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gl.setChecked(false);
                    kr.setChecked(false);
                    vng.setChecked(false);
                    lite.setChecked(false);
                    bgmi.setChecked(false);

                    versionName.setText("Version: Taiwan");

                    extLocName = "com.rekoo.pubgm";
                }

                if (!tw.isEnabled()) {
                    Toast.makeText(ModActivity.this, "Mod Already Applied on " + versionName.getText().toString() + "\n So firstly stop MOD to continue!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gl.setChecked(false);
                    kr.setChecked(false);
                    vng.setChecked(false);
                    tw.setChecked(false);
                    bgmi.setChecked(false);

                    versionName.setText("Version: Lite");

                    extLocName = "com.tencent.iglite";
                }

                if (!lite.isEnabled()) {
                    Toast.makeText(ModActivity.this, "Mod Already Applied on " + versionName.getText().toString() + "\n So firstly stop MOD to continue!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bgmi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gl.setChecked(false);
                    kr.setChecked(false);
                    vng.setChecked(false);
                    tw.setChecked(false);
                    lite.setChecked(false);

                    versionName.setText("Version: BGMI");

                    extLocName = "com.pubg.imobile";
                }

                if (!bgmi.isEnabled()) {
                    Toast.makeText(ModActivity.this, "Mod Already Applied on " + versionName.getText().toString() + "\n So firstly stop MOD to continue!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Select Mod Type


        mod1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!extLocName.isEmpty()) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(extLocName);
                    if (launchIntent != null) {
                        String modName = "mod1";
                        modtype.setText("Mod: 1");

                        String url = "https://vip-mod-data.000webhostapp.com/download.php?key=" + preKey + "&token=" + preToken + "&file=" + modName + ".zip";

                        Utils.createDir(Environment.getExternalStorageDirectory().toString(), "Android/data/" + extLocName + "/");
                        Utils.createDir(Environment.getExternalStorageDirectory().toString() + "/Android/data/", extLocName);

                        String unzipLocation = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + extLocName + "/";
                        String zipFile = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + modName + "." + "zip";

                        try {
                            new Utils().downloadEventData(ModActivity.this, zipFile, unzipLocation, url);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                        gl.setEnabled(false);
                        kr.setEnabled(false);
                        vng.setEnabled(false);
                        tw.setEnabled(false);
                        lite.setEnabled(false);
                        bgmi.setEnabled(false);

                        Toast.makeText(ModActivity.this, "You cant Change version untill you stop this mod", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModActivity.this, "Version Not Found", Toast.LENGTH_SHORT).show();
                        gl.setChecked(false);
                        kr.setChecked(false);
                        vng.setChecked(false);
                        tw.setChecked(false);
                        lite.setChecked(false);
                        bgmi.setEnabled(false);

                        versionName.setText("Version: ");
                        extLocName = "";
                    }
                } else {
                    Toast.makeText(ModActivity.this, "Select a version Firstly!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!extLocName.isEmpty()) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(extLocName);
                    if (launchIntent != null) {
                        String modName = "mod2";
                        modtype.setText("Mod: 2");


                        String url = "https://vip-mod-data.000webhostapp.com/download.php?file=" + modName + ".zip&token=" + preToken + "&key=" + preKey;

                        Utils.createDir(Environment.getExternalStorageDirectory().toString(), "Android/data/" + extLocName + "/");
                        Utils.createDir(Environment.getExternalStorageDirectory().toString() + "/Android/data/", extLocName);

                        String unzipLocation = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + extLocName + "/";
                        String zipFile = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + modName + "." + "zip";

                        try {
                            new Utils().downloadEventData(ModActivity.this, zipFile, unzipLocation, url);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        gl.setEnabled(false);
                        kr.setEnabled(false);
                        vng.setEnabled(false);
                        tw.setEnabled(false);
                        lite.setEnabled(false);
                        bgmi.setEnabled(false);

                        Toast.makeText(ModActivity.this, "You cant Change version untill you stop this mod", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModActivity.this, "Version Not Found", Toast.LENGTH_SHORT).show();
                        gl.setChecked(false);
                        kr.setChecked(false);
                        vng.setChecked(false);
                        tw.setChecked(false);
                        lite.setChecked(false);
                        bgmi.setEnabled(false);

                        extLocName = "";
                        versionName.setText("Version: ");
                    }
                } else {
                    Toast.makeText(ModActivity.this, "Select a version Firstly!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mod3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!extLocName.isEmpty()) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(extLocName);
                    if (launchIntent != null) {
                        String modName = "mod3";
                        modtype.setText("Mod: 3");

                        String url = "https://vip-mod-data.000webhostapp.com/download.php?file=" + modName + ".zip&token=" + preToken + "&key=" + preKey;

                        Utils.createDir(Environment.getExternalStorageDirectory().toString(), "Android/data/" + extLocName + "/");
                        Utils.createDir(Environment.getExternalStorageDirectory().toString() + "/Android/data/", extLocName);

                        String unzipLocation = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + extLocName + "/";
                        String zipFile = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + modName + "." + "zip";

                        try {
                            new Utils().downloadEventData(ModActivity.this, zipFile, unzipLocation, url);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        gl.setEnabled(false);
                        kr.setEnabled(false);
                        vng.setEnabled(false);
                        tw.setEnabled(false);
                        lite.setEnabled(false);
                        bgmi.setEnabled(false);

                        Toast.makeText(ModActivity.this, "You cant Change version untill you stop this mod", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModActivity.this, "Version Not Found", Toast.LENGTH_SHORT).show();
                        gl.setChecked(false);
                        kr.setChecked(false);
                        vng.setChecked(false);
                        tw.setChecked(false);
                        lite.setChecked(false);
                        bgmi.setEnabled(false);

                        extLocName = "";
                        versionName.setText("Version: ");
                    }
                } else {
                    Toast.makeText(ModActivity.this, "Select a version Firstly!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        modPak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!extLocName.isEmpty()) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(extLocName);
                    if (launchIntent != null) {
                        String modName = "modPak";
                        modtype.setText("Mod: Paks");

                        String url = "https://vip-mod-data.000webhostapp.com/download.php?file=" + modName + ".zip&token=" + preToken + "&key=" + preKey;
                        Utils.createDir(Environment.getExternalStorageDirectory().toString(), "Android/data/" + extLocName + "/");
                        Utils.createDir(Environment.getExternalStorageDirectory().toString() + "/Android/data/", extLocName);

                        String unzipLocation = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + extLocName + "/";
                        String zipFile = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + modName + "." + "zip";

                        try {
                            new Utils().downloadEventData(ModActivity.this, zipFile, unzipLocation, url);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                        gl.setEnabled(false);
                        kr.setEnabled(false);
                        vng.setEnabled(false);
                        tw.setEnabled(false);
                        lite.setEnabled(false);
                        bgmi.setEnabled(false);

                        Toast.makeText(ModActivity.this, "You cant Change version untill you stop this mod", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModActivity.this, "Version Not Found", Toast.LENGTH_SHORT).show();
                        gl.setChecked(false);
                        kr.setChecked(false);
                        vng.setChecked(false);
                        tw.setChecked(false);
                        lite.setChecked(false);
                        bgmi.setEnabled(false);

                        extLocName = "";
                        versionName.setText("Version: ");
                    }
                } else {
                    Toast.makeText(ModActivity.this, "Select a version Firstly!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        modData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!extLocName.isEmpty()) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(extLocName);
                    if (launchIntent != null) {
                        String modName = "modData";
                        modtype.setText("Mod: Data");

                        String url = "https://vip-mod-data.000webhostapp.com/download.php?file=" + modName + ".zip&token=" + preToken + "&key=" + preKey;

                        Utils.createDir(Environment.getExternalStorageDirectory().toString(), "Android/data/" + extLocName + "/");
                        Utils.createDir(Environment.getExternalStorageDirectory().toString() + "/Android/data/", extLocName);

                        String unzipLocation = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + extLocName + "/";
                        String zipFile = Environment.getExternalStorageDirectory() + "/" + "Android" + "/" + "data" + "/" + modName + "." + "zip";

                        try {
                            new Utils().downloadEventData(ModActivity.this, zipFile, unzipLocation, url);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        gl.setEnabled(false);
                        kr.setEnabled(false);
                        vng.setEnabled(false);
                        tw.setEnabled(false);
                        lite.setEnabled(false);
                        bgmi.setEnabled(false);

                        Toast.makeText(ModActivity.this, "You cant Change version untill you stop this mod", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModActivity.this, "Version Not Found", Toast.LENGTH_SHORT).show();
                        gl.setChecked(false);
                        kr.setChecked(false);
                        vng.setChecked(false);
                        tw.setChecked(false);
                        lite.setChecked(false);
                        bgmi.setEnabled(false);

                        extLocName = "";
                        versionName.setText("Version: ");
                    }
                } else {
                    Toast.makeText(ModActivity.this, "Select a version Firstly!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Start or Stop Mod


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!extLocName.isEmpty()) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(extLocName);
                    if (launchIntent != null) {
                        String root = Environment.getExternalStorageDirectory().toString();

                        File file1 = new File(root + "/Android/data/" + extLocName + "/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/MMKV");
                        File file2 = new File(root + "/Android/data/" + extLocName + "/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Logs");
                        File file3 = new File(root + "/Android/data/" + extLocName + "/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames/iTOPPrefs.sav");
                        File file4 = new File(root + "/Android/data/" + extLocName + "/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames/Active.sav");
                        File file5 = new File(root + "/Android/data/" + extLocName + "/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames/Cached.sav");


                        file1.delete();
                        file2.delete();
                        file3.delete();
                        file4.delete();
                        file5.delete();


                        if (file1.exists()) {
                            try {
                                file1.getCanonicalFile().delete();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (file1.exists()) {
                                getApplicationContext().deleteFile(file1.getName());
                            }
                        }
                        if (file2.exists()) {
                            try {
                                file2.getCanonicalFile().delete();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (file2.exists()) {
                                getApplicationContext().deleteFile(file2.getName());
                            }
                        }
                        if (file3.exists()) {
                            try {
                                file3.getCanonicalFile().delete();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (file3.exists()) {
                                getApplicationContext().deleteFile(file3.getName());
                            }
                        }
                        if (file4.exists()) {
                            try {
                                file4.getCanonicalFile().delete();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (file4.exists()) {
                                getApplicationContext().deleteFile(file4.getName());
                            }
                        }
                        if (file5.exists()) {
                            try {
                                file5.getCanonicalFile().delete();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (file5.exists()) {
                                getApplicationContext().deleteFile(file5.getName());
                            }
                        }


                        gl.setEnabled(true);
                        kr.setEnabled(true);
                        vng.setEnabled(true);
                        tw.setEnabled(true);
                        lite.setEnabled(true);
                        bgmi.setEnabled(true);

                    } else {
                        Toast.makeText(ModActivity.this, "Version Not Found", Toast.LENGTH_SHORT).show();
                        gl.setChecked(false);
                        kr.setChecked(false);
                        vng.setChecked(false);
                        tw.setChecked(false);
                        lite.setChecked(false);
                        bgmi.setEnabled(false);

                        extLocName = "";
                        versionName.setText("Version: ");
                    }
                } else {
                    Toast.makeText(ModActivity.this, "Select a Version firstly!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!extLocName.isEmpty()) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(extLocName);
                    if (launchIntent != null) {
                        startActivity(launchIntent);
                    } else {
                        Toast.makeText(ModActivity.this, "Version Not Found", Toast.LENGTH_SHORT).show();
                        gl.setChecked(false);
                        kr.setChecked(false);
                        vng.setChecked(false);
                        tw.setChecked(false);
                        lite.setChecked(false);
                        bgmi.setEnabled(false);

                        extLocName = "";
                        versionName.setText("Version: ");
                    }
                } else {
                    Toast.makeText(ModActivity.this, "Select a version Firstly!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //And 11 Methods
    public void askPermission(final String _uri) {

        if (fromStorage) {
            i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        }
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        i.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);
        i.putExtra(android.provider.DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(_uri));
        startActivityForResult(i, new_folder);
    }

    public boolean checkPermission(final String _uri) {
        Uri muri = Uri.parse(_uri);
        dFile = androidx.documentfile.provider.DocumentFile.fromTreeUri(getApplicationContext(), muri);

        if (dFile.canRead() && dFile.canWrite()) {
            return true;
        }
        return false;
    }

    public String pathToRealUri(String _path) {
        uriFor1 = "content://com.android.externalstorage.documents/tree/primary%3A";

        if (_path.endsWith("/")) {
            _path = _path.substring(0, _path.length() - 1);
        }


        if (_path.contains("/sdcard/")) {
            uriFor2 = _path.replace("/sdcard/", "").replace("/", "%2F");

            if (uriFor2.substring(uriFor2.length() - 1, uriFor2.length()).equals("/")) {

                uriFor2 = uriFor1.substring(0, uriFor1.length() - 1);

            }

        } else {
            if (_path.contains("/storage/") && _path.contains("/emulated/")) {
                uriFor2 = _path.replace("/storage/emulated/0/", "").replace("/", "%2F");

                if (uriFor2.substring(uriFor2.length() - 1, uriFor2.length()).equals("/")) {

                    uriFor2 = uriFor1.substring(0, uriFor1.length() - 1);

                }

            } else {

            }
        }
        return uriFor1 = uriFor1 + uriFor2;
    }

    public String pathToUri(String _path) {
        uriFor1 = "content://com.android.externalstorage.documents/tree/primary%3AAndroid/document/primary%3A";

        if (_path.endsWith("/")) {
            _path = _path.substring(0, _path.length() - 1);
        }

        if (_path.contains("/sdcard/")) {
            uriFor2 = _path.replace("/sdcard/", "").replace("/", "%2F");

            if (uriFor2.substring(uriFor2.length() - 1, uriFor2.length()).equals("/")) {

                uriFor2 = uriFor1.substring(0, uriFor1.length() - 1);

            }


        } else {
            if (_path.contains("/storage/") && _path.contains("/emulated/")) {
                uriFor2 = _path.replace("/storage/emulated/0/", "").replace("/", "%2F");

                if (uriFor2.substring(uriFor2.length() - 1, uriFor2.length()).equals("/")) {

                    uriFor2 = uriFor1.substring(0, uriFor1.length() - 1);

                }

            } else {

            }
        }
        return uriFor1 = uriFor1 + uriFor2;
    }
    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        if (_requestCode == new_folder) {
            if (_resultCode == Activity.RESULT_OK) {
                if (_data != null) {
                    final Uri uri2 = _data.getData();
                    if (Uri.decode(uri2.toString()).endsWith(":")) {
                        SketchwareUtil.showMessage(getApplicationContext(), "⛔");
                        askPermission(uri2.toString());
                    } else {
                        getContentResolver().takePersistableUriPermission(uri2, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


                        SketchwareUtil.showMessage(getApplicationContext(), "permisions granted");


                    }

                } else {

                }
            } else {

                SketchwareUtil.showMessage(getApplicationContext(), "Activity not ok");

            }
        }


        if (_requestCode == 2000) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {

                } else {

                }
            }

        }


    }
}