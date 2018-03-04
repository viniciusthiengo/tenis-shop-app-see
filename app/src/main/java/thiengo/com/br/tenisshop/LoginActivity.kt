package thiengo.com.br.tenisshop

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun callSignUpActivity(view: View){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun callSneakersActivity(view: View){
        val intent = Intent(this, SneakersActivity::class.java)
        startActivity(intent)
        finish()
    }
}
