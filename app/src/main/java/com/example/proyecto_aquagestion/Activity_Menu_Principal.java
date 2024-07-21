package com.example.proyecto_aquagestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Menu_Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);

        // Configuración de los insets para ajustar el padding de la vista principal
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para cerrar sesión y volver a la pantalla principal
    public void cerrar_sesion(View view) {
        Toast.makeText(this, "cerrar sesion", Toast.LENGTH_SHORT).show();
        Intent cerrar_sesion = new Intent(this, MainActivity.class);
        startActivity(cerrar_sesion);
        finish();
    }

    // Método para ingresar al inventario
    public void ingreso_inventario(View view) {
        Toast.makeText(this, "ingreso inventario", Toast.LENGTH_SHORT).show();
        Intent ingreso_inventario = new Intent(this, Activity_Inventario.class);
        startActivity(ingreso_inventario);
    }

    // Método para ingresar a los reportes
    public void ingreso_reportes(View view) {
        Toast.makeText(this, "ingreso reportes", Toast.LENGTH_SHORT).show();
        Intent ingreso_reportes = new Intent(this, Activity_Reporte_Ventas.class);
        startActivity(ingreso_reportes);
    }

    // Método para ingresar a la lista de productos
    public void ingre_producto(View view) {
        Toast.makeText(this, "ingreso productos", Toast.LENGTH_SHORT).show();
        Intent ingre_producto = new Intent(this, Activity_lista_Productos.class);
        startActivity(ingre_producto);
    }

    // Método para realizar una venta
    public void realizar_venta(View view) {
        Toast.makeText(this, "realizar ventas", Toast.LENGTH_SHORT).show();
        Intent realizar_venta = new Intent(this, Activity_realizar_venta.class);
        startActivity(realizar_venta);
    }
}
