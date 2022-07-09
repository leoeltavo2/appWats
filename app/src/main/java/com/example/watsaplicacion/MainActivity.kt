package com.example.watsaplicacion

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.watsaplicacion.ajustes.AjustesActivity
import com.example.watsaplicacion.bases.NuevoContactoActivity
import com.example.watsaplicacion.estados.MostrarEstadosActivity
import com.example.watsaplicacion.fragmentos.CamaraFragment
import com.example.watsaplicacion.fragmentos.ChatsFragment
import com.example.watsaplicacion.fragmentos.EstadosFragment
import com.example.watsaplicacion.fragmentos.LlamadasFragment
import com.example.watsaplicacion.llamadas.InfoLlamadaActivity
import com.example.watsaplicacion.login.TelefonoLogActivity
import com.example.watsaplicacion.mensajes.MensajesActivity
import com.example.watsaplicacion.perfil.PerfilActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    //CAMARA
    var foto: Uri? = null
    private val REQUEST_CAMERA =1002

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



        configurarPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
        iconoCamara()
        viewPager.setCurrentItem(1)

    //        ACCIONES DE LOS BOTONES FLOTANTES
        fabActionMensaje.setOnClickListener { startActivity(Intent(this@MainActivity, NuevoContactoActivity::class.java)) }
        fabActionCamara.setOnClickListener { mostrarCamara()}

        fabActionTelefono.setOnClickListener {Toast.makeText(this,"llamar", Toast.LENGTH_LONG).show() }


        //PARA PODER CAMBIAR EL ICONO
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                cambiarIcono(position)
            }
        })


    }

    //ABRIR LA CAMARA
    fun mostrarCamara(){
        var view = layoutInflater.inflate(R.layout.activity_main,null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        view.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

            if (checkSelfPermission(android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){

                val permisoCamara = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permisoCamara,REQUEST_CAMERA )
            }else{
                tomarFoto()
            }
                dialog.dismiss()
    }

    private fun tomarFoto() {
        val valor = ContentValues()
        valor.put(MediaStore.Images.Media.TITLE,"Nueva imagen")
        foto = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valor)
        val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, foto)
        startActivityForResult(camaraIntent,REQUEST_CAMERA)
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
//    CIERRE ABRIR CAMARA

    //ICONO DE LA CAMARA
    fun iconoCamara(){
        tabLayout.getTabAt(0)?.setIcon(R.drawable.camara)
        val linearLayout = (tabLayout.getChildAt(0) as LinearLayout).getChildAt(0) as LinearLayout
        val layoutParams = linearLayout.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 0.5f
        linearLayout.layoutParams = layoutParams
    }

    //AGREGAR LOS FRAGMENTOS
    private fun configurarPager(viewPager: ViewPager) {
        val adapter: com.example.watsaplicacion.MainActivity.SectionPagerAdapter =
            com.example.watsaplicacion.MainActivity.SectionPagerAdapter(supportFragmentManager)
        adapter.addFragment(CamaraFragment(), "")
        adapter.addFragment(ChatsFragment(), "CHATS")
        adapter.addFragment(EstadosFragment(), "ESTADOS")
        adapter.addFragment(LlamadasFragment(), "LLAMADAS")
        viewPager.adapter = adapter

    }

    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.buscar -> Toast.makeText(
                this@MainActivity,
                "pushaskfkfkfkkdkdkdkdkdte buscar",
                Toast.LENGTH_LONG
            ).show()
            R.id.nuevo_grupo -> Toast.makeText(this@MainActivity, "Nuevo grupo", Toast.LENGTH_LONG)
                .show()
            R.id.nueva_dif -> Toast.makeText(this@MainActivity, "Nueva difusion", Toast.LENGTH_LONG)
                .show()
            R.id.whats_web -> Toast.makeText(this@MainActivity, "whatsApp web", Toast.LENGTH_LONG)
                .show()
            R.id.mensajes_dest -> Toast.makeText(
                this@MainActivity,
                "mensajes destacados",
                Toast.LENGTH_LONG
            ).show()
            R.id.ajustes -> startActivity(Intent(this@MainActivity, AjustesActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    //SECTIONPAGERADAPTER DENTRO DE MAIN
    private class SectionPagerAdapter(manager: FragmentManager?) :
        FragmentPagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

    //CAMBIAR ICONO
    private fun cambiarIcono(index: Int) {
        fabActionMensaje.hide()
        fabActionCamara.hide()
        fabActionTelefono.hide()
        when (index) {
            0 -> {
                fabActionMensaje.hide()
                fabActionCamara.hide()
                fabActionTelefono.hide()
            }
            1 -> fabActionMensaje.show()
            2 -> fabActionCamara.show()
            3 -> fabActionTelefono.show()
        }

    }
}