package com.example.proyecto_aquagestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Activity_Productos extends AppCompatActivity {

    private ListView listViewProductos;
    private BD_Producto dbHelper;
    private ArrayAdapter<String> productAdapter;
    private List<Producto> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        // Configuración de la vista
        listViewProductos = findViewById(R.id.listViewProductos);
        Button btnAgregar = findViewById(R.id.btnAgregar);

        // Inicializar BD_Producto
        dbHelper = new BD_Producto(this);

        // Cargar productos de la base de datos
        loadProducts();

        // Manejar clics en los elementos de la lista
        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto product = productList.get(position);
                Intent intent = new Intent(Activity_Productos.this, Editar_Producto.class);
                intent.putExtra("productId", product.getId());
                startActivity(intent);
            }
        });

        // Manejar clic en el botón Agregar
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Productos.this, Activity_registrarProducto.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recargar la lista de productos al reanudar la actividad
        loadProducts();
    }

    private void loadProducts() {
        // Obtener todos los productos de la base de datos
        productList = dbHelper.getAllProducts();

        // Convertir la lista de productos a una lista de nombres
        List<String> productNames = new ArrayList<>();
        for (Producto product : productList) {
            productNames.add(product.getName());
        }

        // Configurar el adaptador para el ListView
        productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productNames);
        listViewProductos.setAdapter(productAdapter);
    }

    public void salir_productos(View view) {
        Toast.makeText(this, "salir_productos", Toast.LENGTH_SHORT).show();
        Intent salir_productos = new Intent(this, Activity_Menu_Principal.class);
        startActivity(salir_productos);
        finish();
    }

    public void agregar_productos(View view) {
        Toast.makeText(this, "agregar productos", Toast.LENGTH_SHORT).show();
        Intent agregar_productos = new Intent(this, Activity_registrarProducto.class);
        startActivity(agregar_productos);
    }
}
