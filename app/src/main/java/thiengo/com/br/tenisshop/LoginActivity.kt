package thiengo.com.br.tenisshop

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.appsee.Appsee
import thiengo.com.br.tenisshop.data.Database


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Appsee.start("Sua_chave_de_api_fornecida_pela_appsee_no_cadastro_do_app")

        /*
         * Se o usuário já tiver logado no aplicativo anteriormente,
         * no mesmo device, ele já entrará direto. Lembrando que esse
         * trecho é simulado, pois o app é de testes e não há um
         * algoritmo completo de login.
         * */
        if( Database.isUserLogged(this) ){
            callSneakersActivity(null)
        }
    }

    fun callSignUpActivity(view: View?){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun callSneakersActivity(view: View?){
        /*
         * Modificando o valor de login do usuário para garantir que
         * ele já entre na área de listagem de tênis na próxima
         * abertura de aplicativo.
         * */
        Database.setLoggedUser(this, true)

        val it = Intent(this, SneakersActivity::class.java)
        startActivity(it)
        finish()
    }
}
