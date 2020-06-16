package hn.edu.ujcv.clase4

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Button

import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.android.synthetic.main.content_detalle.*


// pasos para pasar de una actividad a otra
// 1. Definir un Intent.
//  1.1. colocar infromacion en el Diccionario Extras.
// 2 llamar StartActivity o StartActivityForResult
//  2.1 si se llamo StartActivityForResult definir onActivityResult


class Detalle : AppCompatActivity() {
    val OBTENER_IMAGEN = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        title = this.intent.extras.getString("titulo")

        val btnEQ = findViewById<Button?>(R.id.btnEmailQueja);
        if(btnEQ != null){
            btnEQ.setOnClickListener(View.OnClickListener {
                val intent  = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_EMAIL,"kenneth.vittetoe@ujcv.edu.hn")
                intent.putExtra(Intent.EXTRA_SUBJECT,"alumno:")


                startActivity(intent)
            })
        }

        btnSelfie.setOnClickListener(View.OnClickListener {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivityForResult(intent,OBTENER_IMAGEN)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == OBTENER_IMAGEN){
            if (resultCode == Activity.RESULT_OK){
                val imageBitmap = data?.extras?.get("data") as Bitmap
                imageView.setImageBitmap(imageBitmap)
            }
        }
    }

    fun onObtenerNombre(view: View){

        intent.putExtra("nuevoTitulo",editText.text.toString())
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    fun onCancel(view:View){
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

}
