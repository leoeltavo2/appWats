package com.example.watsaplicacion.perfil

import android.content.ContentResolver
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import android.view.textclassifier.TextLinks
import android.webkit.PermissionRequest
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.request.Request
import com.example.watsaplicacion.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.foto_perfil_sheet.*
import java.util.*
import java.util.jar.Manifest

class PerfilActivity : AppCompatActivity() {
    private lateinit var imagen: ImageView
    var foto:Uri? = null
    private val REQUEST_CAMERA =1002

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        fabActionCamPerfil.setOnClickListener{ mostrarSheet() }
        imagen = findViewById(R.id.imagenPerfil) as ImageView


    }

    fun mostrarSheet(){
        var view = layoutInflater.inflate(R.layout.foto_perfil_sheet,null)



        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        view.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

        (view.findViewById(R.id.btnGaleria) as View).setOnClickListener {
            elegirImagen()
            dialog.dismiss()
        }
        (view.findViewById(R.id.btnCamaraPic) as View).setOnClickListener {
            if (checkSelfPermission(android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){

            val permisoCamara = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            requestPermissions(permisoCamara,REQUEST_CAMERA )
            }else{
            tomarFoto()
            dialog.dismiss()
            }
        }
    }
    //SELECCIONAR IMAGEN
    fun elegirImagen(){
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/*")
        startActivityForResult(Intent.createChooser(intent,"seleccione una aplicacion"),10)
    }
    //    CAMARA
    fun tomarFoto(){
        val valor = ContentValues()
        valor.put(MediaStore.Images.Media.TITLE,"Nueva imagen")
        foto = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valor)
        val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, foto)
        startActivityForResult(camaraIntent,REQUEST_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== RESULT_OK){
            var path: Uri? = data?.data
            imagen.setImageURI(path)
        }
        if (resultCode == RESULT_OK && requestCode == REQUEST_CAMERA){
            imagen.setImageURI(foto)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CAMERA -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) tomarFoto()
                else Toast.makeText(this,"No tienes permisos para abrir la camara",Toast.LENGTH_LONG).show()
            }
        }
    }

}