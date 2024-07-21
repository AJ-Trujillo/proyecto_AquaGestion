package com.example.proyecto_aquagestion;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class Activity_registrarProducto extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText etNombreProducto, etCapacidadProducto, etDescripcionProducto, etPrecioProducto;
    private Spinner spinnerUnidad;
    private ImageView ivProductImage;
    private Button btnRegistrar, btnSubirImagen, btnCancelar;
    private Uri imageUri;
    private BD_Producto dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroproductos);

        etNombreProducto = findViewById(R.id.etNombreProducto);
        etCapacidadProducto = findViewById(R.id.etCapacidadProducto);
        etDescripcionProducto = findViewById(R.id.etDescripcionProducto);
        etPrecioProducto = findViewById(R.id.etPrecioProducto);
        spinnerUnidad = findViewById(R.id.spinnerUnidad);
        ivProductImage = findViewById(R.id.ivProductImage);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnSubirImagen = findViewById(R.id.btnSubirImagen);
        btnCancelar = findViewById(R.id.btnCancelar);

        dbHelper = new BD_Producto(this);

        btnSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSelectorDeArchivos();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarProducto();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirRegistro();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void abrirSelectorDeArchivos() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivProductImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void agregarProducto() {
        String nombre = etNombreProducto.getText().toString();
        String capacidad = etCapacidadProducto.getText().toString();
        String descripcion = etDescripcionProducto.getText().toString();
        String precio = etPrecioProducto.getText().toString();
        String unidad = spinnerUnidad.getSelectedItem().toString();

        if (nombre.isEmpty() || capacidad.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Producto producto = new Producto(nombre, capacidad + " " + unidad, descripcion, precio, imageUri != null ? imageUri.toString() : null);
        dbHelper.addProduct(producto);
        Toast.makeText(this, "Producto agregado exitosamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void salirRegistro() {
        Toast.makeText(this, "Salir registro", Toast.LENGTH_SHORT).show();
        Intent salirRegistro = new Intent(this, Activity_Productos.class);
        startActivity(salirRegistro);
        finish();
    }

}
