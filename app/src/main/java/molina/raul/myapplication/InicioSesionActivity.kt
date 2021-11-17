package molina.raul.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class InicioSesionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        val botoniniciosesion = findViewById<Button>(R.id.buttoniniciosesion)
        val botonolvidar = findViewById<TextView>(R.id.olvidar)
        val botonregistrar = findViewById<Button>(R.id.registar)



        botoniniciosesion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        botonolvidar.setOnClickListener {

        }
        botonregistrar.setOnClickListener {
            val intent = Intent(this, RegistrarseActivity::class.java)
            startActivity(intent)
        }



    }
}