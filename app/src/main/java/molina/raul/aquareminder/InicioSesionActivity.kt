package molina.raul.aquareminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_inicio_sesion.*
import molina.raul.aquareminder.ui.notifications.NotificationsFragment

class InicioSesionActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient

    //val botongoogle: Button = findViewById(R.id.sign_in_button)
    val RC_SIGN_IN = 123
    val COD_LOGOUT = 321

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        val botoniniciosesion = findViewById<Button>(R.id.buttoniniciosesion)
        val botonolvidar = findViewById<TextView>(R.id.olvidar)
        val botonregistrar = findViewById<Button>(R.id.registar)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        sign_in_button.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
        if (requestCode == COD_LOGOUT) {
            signOut()
        }
    }

    override fun onStart() {
        super.onStart()
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                Toast.makeText(this, "Sesion Terminada", Toast.LENGTH_SHORT).show()
            }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", account.displayName)
            intent.putExtra("email", account.email)
            intent.putExtra("photo", account.photoUrl)

            /*val bundle = Bundle()
            bundle.putString("nombre", account.displayName)
            val fragobj = NotificationsFragment()
            fragobj.setArguments(bundle)*/


            startActivityForResult(intent, COD_LOGOUT)
        }
    }


    //Puente entre actividad y fragmento
    //no funciona
    fun newInstance(account: GoogleSignInAccount?): NotificationsFragment? {
        val f = NotificationsFragment()
        // Supply index input as an argument.
        val args = Bundle()
        if (account != null) {
            args.putString("name", account.displayName)
            //args.putString("email", account.email)
            f.setArguments(args)
        }
        return f
    }


}