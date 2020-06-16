package hn.edu.ujcv.clase4

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val actividadPrincipalRC = 45
    val actividadContactos = 88

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMTexto.setOnClickListener(View.OnClickListener {
            textView.text = "Texto Modificado"
            it.isEnabled = false
        })

        val btnEQ = findViewById<Button?>(R.id.btnEmailQueja);
        if(btnEQ != null){
            btnEQ.setOnClickListener(View.OnClickListener {
                val intent  = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_EMAIL,"quejas@ujcv.edu.hn")
                intent.putExtra(Intent.EXTRA_SUBJECT,"App Ejemplo")

                startActivity(intent)
            })
        }
    }

    fun toDetail( v: View){
        val intent = Intent(this, Detalle::class.java)


        intent.putExtra("titulo","Formulario Simple")

        startActivityForResult(intent,actividadPrincipalRC)
        //startActivity(intent)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == actividadPrincipalRC){
            if(resultCode != Activity.RESULT_CANCELED){
                val nuevoTitulo: String? = "Hola "+ data?.extras?.getString("nuevoTitulo")
                title = nuevoTitulo
            }
            else{
                Snackbar.make(button, "Cancelamos el renombramiento!!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
        if(requestCode == actividadContactos){

        }

    }

}
