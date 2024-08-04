package com.thirtyseventyc.gpian20;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.net.Uri.EMPTY;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpian20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class fragPost extends Fragment {

    String ipo;
    int num = 0;
    int ipost = 2;
    String classSelected;
    Uri pdfselected;

    Boolean isSelected = false;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    String downloadurl;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, String> thisPOst = new HashMap<>();
    Map<String, String> updateCount = new HashMap<>();


    private String teachername="Sarthak"; //get from myprofile
    private String pdftitle;
    private String pdfdesc;
    private Spinner classesspinner;
    private EditText getTitle;
    private EditText getDesc;
    private Button selectPdf;
    private Button uploadButton;
    private TextView titleWarning;
    Boolean isAttachment = false;


    public fragPost() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            tempButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    thisPOst.put("title", titleNotice.getText().toString());
//                    thisPOst.put("desc", descNotice.getText().toString());
//                    thisPOst.put("url", urlNotice.getText().toString());
//                    Toast.makeText(getActivity().getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
//
//                    uploadNewNotice();
//                    updateThePostCount();
//                }
//            });


        }
    }

    public void selectFile() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select your PDF...."), 1);
        isSelected = true;
    }
    String pdfFilename;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
//                uploadFiles(data.getData(), pdftitle);
            pdfselected=data.getData();
            pdfFilename =  getOriginalFilename(pdfselected);
            TextView filoShow = getActivity().findViewById(R.id.textView4);
            filoShow.setText(pdfFilename);
        }
    }

    @SuppressLint("Range")
    private String getOriginalFilename(Uri uri) {
        String filename = null;

        if (uri.getScheme().equals("content")) {
            ContentResolver contentResolver = getActivity().getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    filename = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        if (filename == null) {
            filename = uri.getPath();
            int index = filename.lastIndexOf("/");
            if (index != -1) {
                filename = filename.substring(index + 1);
            }
        }

        return filename;
    }



    String urlNotice;
    private void uploadNewNotice() {
        EditText titleNotice = getActivity().findViewById(R.id.postTitle);
        EditText descNotice = getActivity().findViewById(R.id.postDesc);

        thisPOst.put("title", titleNotice.getText().toString());
        thisPOst.put("desc", descNotice.getText().toString());
        Toast.makeText(getActivity().getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        db.collection("All Posts").document("Post" + ipost)
                .set(thisPOst)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity().getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "success");
                        titleNotice.setText("");
                        descNotice.setText("");
                        TextView filoShow = getActivity().findViewById(R.id.textView4);
                        filoShow.setText("Add PDF");

                        updateThePostCount();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), "NOt Done", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "get failed with for dp");

                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_frag_post, container, false);
        CardView tempButton = (CardView) view.findViewById(R.id.addNewPost);
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isSelected == true){
                    uploadFiles(pdfselected);
                    Toast.makeText(getActivity().getApplicationContext(), "Selected Hai", Toast.LENGTH_SHORT).show();
                }
                else{
                    thisPOst.put("attachment", String.valueOf(isAttachment));
                    uploadNewNotice();
                    Toast.makeText(getActivity().getApplicationContext(), "EMPTY", Toast.LENGTH_SHORT).show();
                }

                EditText titleNotice = getActivity().findViewById(R.id.postTitle);
            }
            public void uploadFiles(Uri data) {

                StorageReference reference= storageReference.child(classSelected+"/"+"Post"+ipost+".pdf");
                reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri=uriTask.getResult();
                        downloadurl= reference.getDownloadUrl().toString();

                        String link = "";
                        String fileUrl = uri.toString();
                        thisPOst.put("url", fileUrl);
                        // Load the PDF URL into the WebView
                        isAttachment = true;
                        thisPOst.put("attachment", String.valueOf(isAttachment));
                        //   addInfoToDatabase(classSelected,pdftitle,pdfdesc,downloadurl,teachername);
                        Toast.makeText(getActivity().getApplicationContext(), "File Uploaded!", Toast.LENGTH_SHORT).show();
                        uploadNewNotice();
                        pdfselected = EMPTY;
                        isSelected = false;

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double  progress=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                    }
                });}



        });


        CardView uploadPDF = (CardView) view.findViewById(R.id.addthePDF);
        LinearLayout UPlod = (LinearLayout) view.findViewById(R.id.addPDF);

        UPlod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                selectFile();

            }
        });
        uploadPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                selectFile();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getCurrentNumberOfPosts();
    }

    private void getCurrentNumberOfPosts() {
        try {
            DocumentReference docRef = db.collection("NumberOfCurrentPost").document("whichPost?");
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        ipo = document.getString("Count");
//                            String temp1 = document.getString("Number");
//                            num = Integer.parseInt(temp1);
                        num = Integer.parseInt(ipo);
                        ipost = Integer.parseInt(ipo);
                        Log.d(TAG, "ipost = " + ipost);

                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }
        catch(Exception e)
        {
            Log.d(TAG, "Failed to get the Number");
        }
    }

    private void updateThePostCount() {
        DocumentReference docRef = db.collection("NumberOfCurrentPost").document("whichPost?");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    ipo = document.getString("Count");
//                            String temp1 = document.getString("Number");
//                            num = Integer.parseInt(temp1);
                    num = Integer.parseInt(ipo) + 1;
                    ipost = Integer.parseInt(ipo) + 1;
                    updateCount.put("Count", String.valueOf(ipost));
                    DocumentReference docRef2 = db.collection("NumberOfCurrentPost").document("whichPost?");
                    docRef2.set(updateCount);

                    if (document.exists()) {
                        Log.d(TAG, "Number Updated: " + ipost);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }



}