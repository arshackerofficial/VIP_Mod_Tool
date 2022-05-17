////TODO
///*
//Programmer Mahmoud Adel
//His channel Hacker onion
//*/
//protected void onActivityResult(int _requestCode,int _resultCode,Intent _data){
//        super.onActivityResult(_requestCode,_resultCode,_data);
//
//        if(_requestCode==new_folder){
//        if(_resultCode==Activity.RESULT_OK){
//        if(_data!=null){
//final Uri uri2=_data.getData();
//        if(Uri.decode(uri2.toString()).endsWith(":")){
//        SketchwareUtil.showMessage(getApplicationContext(),"⛔");
//        askPermission(uri2.toString());
//        }
//        else{
//final int takeFlags=i.getFlags()
//        &(Intent.FLAG_GRANT_READ_URI_PERMISSION
//        |Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//// Check for the freshest data.
//        getContentResolver().takePersistableUriPermission(uri2,takeFlags);
//
//
//        SketchwareUtil.showMessage(getApplicationContext(),"تم منح الازونات");
//
//
//        }
//
//        }else{
//
//        }
//        }else{
//
//        SketchwareUtil.showMessage(getApplicationContext(),"خطأ عليك منح الازونات");
//
//        }
//        }
//
//
//        if(_requestCode==2000){
//        if(android.os.Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
//        if(Environment.isExternalStorageManager()){
//
//        }else{
//
//        }
//        }
//
//        }
//
//
//        }
//
//// solve android 11 sdcard permissions
//
//
//public void RequestPermission_Dialog(){
//        if(android.os.Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
//        try{
//        Intent intent=new Intent(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.setData(Uri.parse(String.format("package: ",new Object[]{getApplicationContext().getPackageName()})));
//        startActivityForResult(intent,2000);
//        }catch(Exception e){
//        Intent obj=new Intent();
//        obj.setAction(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//        startActivityForResult(obj,2000);
//        }
//        }else{
//        androidx.core.app.ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);
//        }
//        }
//
//public boolean permission(){
//        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.R){ // R is Android 11
//        return Environment.isExternalStorageManager();
//        }else{
//        int write=androidx.core.content.ContextCompat.checkSelfPermission(getApplicationContext(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        int read=androidx.core.content.ContextCompat.checkSelfPermission(getApplicationContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE);
//
//        return write==android.content.pm.PackageManager.PERMISSION_GRANTED
//        &&read==android.content.pm.PackageManager.PERMISSION_GRANTED;
//        }
//        }
//
//// ask permissions request
//
//public void askPermission(final String _uri){
//
//        if(fromStorage){
//        i=new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        }
//        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        i.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);
//        i.putExtra(android.provider.DocumentsContract.EXTRA_INITIAL_URI,Uri.parse(_uri));
//        startActivityForResult(i,new_folder);
//        }
//
//// check permissions of path if accepted
//
//
//public boolean checkPermission(final String _uri){
//        Uri muri=Uri.parse(_uri);
//        dFile=androidx.documentfile.provider.DocumentFile.fromTreeUri(getApplicationContext(),muri);
//
//        if(dFile.canRead()&&dFile.canWrite()){
//        return true;
//        }
//        return false;
//        }
//
//// simple path to UriTree path
//
//
//public String pathToRealUri(String _path){
//        uriFor1="content://com.android.externalstorage.documents/tree/primary%3A";
//
//        if(_path.endsWith("/")){
//        _path=_path.substring(0,_path.length()-1);
//        }
//
//
//        if(_path.contains("/sdcard/")){
//        uriFor2=_path.replace("/sdcard/","").replace("/","%2F");
//
//        if(uriFor2.substring(uriFor2.length()-1,uriFor2.length()).equals("/")){
//
//        uriFor2=uriFor1.substring(0,uriFor1.length()-1);
//
//        }
//
//        }
//        else{
//        if(_path.contains("/storage/")&&_path.contains("/emulated/")){
//        uriFor2=_path.replace("/storage/emulated/0/","").replace("/","%2F");
//
//        if(uriFor2.substring(uriFor2.length()-1,uriFor2.length()).equals("/")){
//
//        uriFor2=uriFor1.substring(0,uriFor1.length()-1);
//
//        }
//
//        }
//        else{
//
//        }
//        }
//        return uriFor1=uriFor1+uriFor2;
//        }
//
//
//// simple path to UriTree path 2
//
//public String pathToUri(String _path){
//        uriFor1="content://com.android.externalstorage.documents/tree/primary%3AAndroid/document/primary%3A";
//
//        if(_path.endsWith("/")){
//        _path=_path.substring(0,_path.length()-1);
//        }
//
//        if(_path.contains("/sdcard/")){
//        uriFor2=_path.replace("/sdcard/","").replace("/","%2F");
//
//        if(uriFor2.substring(uriFor2.length()-1,uriFor2.length()).equals("/")){
//
//        uriFor2=uriFor1.substring(0,uriFor1.length()-1);
//
//        }
//
//
//        }
//        else{
//        if(_path.contains("/storage/")&&_path.contains("/emulated/")){
//        uriFor2=_path.replace("/storage/emulated/0/","").replace("/","%2F");
//
//        if(uriFor2.substring(uriFor2.length()-1,uriFor2.length()).equals("/")){
//
//        uriFor2=uriFor1.substring(0,uriFor1.length()-1);
//
//        }
//
//        }
//        else{
//
//        }
//        }
//        return uriFor1=uriFor1+uriFor2;
//        }
//
//// ccopy file from path to path
//
//private boolean copyAsset(final String assetFilename,final Uri targetUri){
//        try{
//        int count;
//        InputStream input=null;
//        OutputStream output=null;
//
//        ContentResolver content=getApplicationContext().getContentResolver();
//
//        input=getApplicationContext().getAssets().open(assetFilename);
//
//        output=content.openOutputStream(targetUri);
//
//
//        byte data[]=new byte[1024];
//        while((count=input.read(data))>0){
//        output.write(data,0,count);
//        }
//        output.flush();
//        output.close();
//        input.close();
//
//        SketchwareUtil.showMessage(getApplicationContext(),"success ✔️ نجاح ");
//
//        }catch(Exception e){
//
//        SketchwareUtil.showMessage(getApplicationContext(),e.toString());
//        return false;
//        }
//
//        return true;
//        }
//
//
//        public boolean copyFiles(Context context,Uri fileUri,Uri targetUri)
//        {
//        InputStream is=null;
//        OutputStream os=null;
//
//
//        try{
//        try{
//
//        ContentResolver content=context.getContentResolver();
//        is=content.openInputStream(fileUri);
//        os=content.openOutputStream(targetUri);
//
//        byte[]buffer=new byte[1024];
//        int length;
//        while((length=is.read(buffer))>0){
//        os.write(buffer,0,length);
//        }
//
//        }finally{
//        is.close();
//        os.close();
//        }
//        }catch(IOException e){
//        return false;
//        }
//
//        return true;
//        }
//
//
//        private boolean fromStorage=false;
//        final static int REQUEST_CODE=333;
//        final static int OLD_REQUEST=2000;
//        private SharedPreferences sha;
//        private Intent i=new Intent();
//        private Uri muri;
//        private String uriFor1="";
//        private String uriFor2="";
//        private
//        androidx.documentfile.provider.DocumentFile dFile;
//        private double PermissionNumber;
//        private static final int new_folder=43;
//        {