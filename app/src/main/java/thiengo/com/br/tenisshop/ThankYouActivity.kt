package thiengo.com.br.tenisshop

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_thank_you.*
import kotlinx.android.synthetic.main.content_thank_you.*
import thiengo.com.br.tenisshop.domain.Sneaker
import thiengo.com.br.tenisshop.domain.Util
import java.util.*


class ThankYouActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sneaker = intent.getParcelableExtra<Sneaker>(Sneaker.KEY)

        /* CÓDIGO DE RASTREAMENTO DE COMPRA */
        tv_tracking_code.text = "Código de rastreamento: ${codeBuyGenerator()}"

        /* MODELO TÊNIS */
        tv_model.text = sneaker.model

        /* MARCA */
        iv_brand.setImageResource(sneaker.brand.imageResource)
        iv_brand.contentDescription = sneaker.brand.name

        /* IMAGEM */
        iv_sneaker.setImageResource( sneaker.imageResource )
        iv_sneaker.contentDescription = "Tênis ${sneaker.model}"
        Util.setImageViewBgColor(this, iv_sneaker)

        /* PREÇO */
        tv_price.text = sneaker.getPriceAsString()
    }

    /*
     * Método que simula a criação de uma código de compra
     * para rastreamento de mercadoria.
     * */
    fun codeBuyGenerator(): String {
        val random = Random()
        var code = ""

        for( i in 0..18 ){
            code += random.nextInt(10).toString()
        }
        return code
    }

    fun backToTenisShop(view: View){
        val it = Intent(this, SneakersActivity::class.java)
        /*
         * Certificando de que não haverá nenhuma outra atividade
         * na pilha de atividades quando o button de volta for
         * acionado.
         * */
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}
