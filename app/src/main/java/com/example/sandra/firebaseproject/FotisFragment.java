package com.example.sandra.firebaseproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class FotisFragment extends Fragment {
    private final String ruta_fotos = Environment.DIRECTORY_DCIM;
    ImageButton foto;

    public FotisFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fotis, container, false);
        foto = (ImageButton) rootView.findViewById(R.id.camerafotos);
        foto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String file = ruta_fotos + NomFoto() + ".jpg";
                File fotito = new File(file);
                try {
                    fotito.createNewFile();
                } catch (IOException ex) {
                }

                Uri uri = Uri.fromFile(fotito);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(cameraIntent, 0);
            }

        });

        return rootView;
    }

    private String NomFoto() {
        long rand = (long) Math.floor(Math.random() * 5871);
        String photoCode = "pic_" + rand;
        return photoCode;
    }
}

