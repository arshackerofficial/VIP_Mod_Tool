package com.arshackerofficial.vipmodtool;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class UnzipUtil {
    private String _zipFile;
    private String _location;
    private ContentResolver resolver;

    public UnzipUtil(String zipFile, String location, ContentResolver content) {
        _zipFile = zipFile;
        _location = location;
        resolver = content;

        _dirChecker("");
    }

    public void unzip() {
        try  {
            FileInputStream fin = new FileInputStream(_zipFile);
            ZipInputStream zin = new ZipInputStream(fin);

            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                Log.v("Decompress", "Unzipping " + ze.getName());

                if(ze.isDirectory()) {
                    _dirChecker(ze.getName());
                } else {
                    OutputStream fout = resolver.openOutputStream(Uri.fromFile(new File(_location + ze.getName())));
                    byte[] buffer = new byte[8192];
                    int len;
                    while ((len = zin.read(buffer)) != -1) {
                        fout.write(buffer, 0, len);
                    }
                    fout.close();
                    zin.closeEntry();
                }
            }
            zin.close();
        } catch(Exception e) {
            Log.e("Decompress", "unzip", e);
        }
    }

    private void _dirChecker(String dir) {
        File f = new File(_location + dir);

        if(!f.isDirectory()) {
            f.mkdirs();
        }
    }
}