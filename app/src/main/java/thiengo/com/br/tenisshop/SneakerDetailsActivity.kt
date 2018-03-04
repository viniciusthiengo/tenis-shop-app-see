package thiengo.com.br.tenisshop

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_sneaker_details.*
import kotlinx.android.synthetic.main.content_sneaker_details.*
import thiengo.com.br.tenisshop.domain.Sneaker
import thiengo.com.br.tenisshop.domain.Util


class SneakerDetailsActivity : AppCompatActivity(),
        View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    var model : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sneaker_details)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar
                .make(view, "Conteúdo compartilhado", Snackbar.LENGTH_LONG)
                .setAction("Compartilhar", null)
                .show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*
         * Foi prefirível utilizar sneaker como uma propriedade local,
         * pois assim evitaremos a necessidade de sempre ter de verificar
         * null.
         * */
        val sneaker = intent.getParcelableExtra<Sneaker>(Sneaker.KEY)

        /* MODELO TÊNIS */
        model = sneaker.model

        /* MARCA */
        iv_brand.setImageResource(sneaker.brand.imageResource)
        iv_brand.contentDescription = sneaker.brand.name

        /* GÊNERO */
        Util.setGenre(sneaker, iv_genre_male, iv_genre_female)

        /* GALERIA DE IMAGENS */
        iv_sneaker_01.setImageResource( sneaker.imageResource )
        iv_sneaker_01.contentDescription = "Tênis ${sneaker.model}"
        Util.setImageViewBgColor(this, iv_sneaker_01)

        iv_sneaker_02.setImageResource( sneaker.album[0] )
        Util.setImageViewBgColor(this, iv_sneaker_02)
        iv_sneaker_03.setImageResource( sneaker.album[1] )
        Util.setImageViewBgColor(this, iv_sneaker_03)
        iv_sneaker_04.setImageResource( sneaker.album[2] )
        Util.setImageViewBgColor(this, iv_sneaker_04)

        /* PREÇOS, QUANTIDADE EM ESTOQUE E BOTÃO COMPRAR */
        tv_price.text = "${sneaker.getPriceAsString()} ou em"
        tv_price_parcels.text = "até ${sneaker.getPriceParcelsAsString()}"
        bt_buy.setOnClickListener(this)

        sp_amount.setOnItemSelectedListener(this)

        /* RATING, COLOCANDO AS ESTRELAS CORRETAS */
        tv_rating_amount.text = "(${sneaker.rating.amount})"
        Util.setStar(window.decorView, R.id.iv_rating_star_01, 1, sneaker.rating.stars)
        Util.setStar(window.decorView, R.id.iv_rating_star_02, 2, sneaker.rating.stars)
        Util.setStar(window.decorView, R.id.iv_rating_star_03, 3, sneaker.rating.stars)
        Util.setStar(window.decorView, R.id.iv_rating_star_04, 4, sneaker.rating.stars)
        Util.setStar(window.decorView, R.id.iv_rating_star_05, 5, sneaker.rating.stars)
    }

    /*
     * Hackcode para que o título da Toolbar seja alterado de
     * acordo com o tênis acionado em lista.
     * */
    override fun onResume() {
        super.onResume()
        toolbar.title = model
    }

    /*
     * Somente para apresentarmos o menu item de "adicionar ao
     * carrinho"
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_sneaker_details, menu)
        return true
    }

    override fun onClick(view: View?) {
        callPaymentDialog()
    }

    /*
     * Hackcode para centralizar o item selecionado no Spinner de
     * quantidade a comprar de um mesmo modelo de tênis.
     * */
    override fun onItemSelected(
        adapterView: AdapterView<*>,
        view: View,
        i: Int,
        l: Long ) {

        (adapterView.getChildAt(0) as TextView).gravity = Gravity.CENTER
    }
    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    /*
     * Abre o dialog de pagamento com cartão de crédito. AlertDialog
     * está sendo utilizado por já ser suficiente a essa necessidade
     * do aplicativo, mas um DialogFragment poderia ser utilizado
     * também.
     * */
    fun callPaymentDialog() {
        val sneaker = intent.getParcelableExtra<Sneaker>(Sneaker.KEY)
        val builder = AlertDialog.Builder(this)
        val dialogLayout = getLayoutInflater()
                .inflate( R.layout.dialog_payment,null )

        builder.setView(dialogLayout)

        dialogLayout
            .findViewById<TextView>(R.id.tv_total_price)
            .text = sneaker.getPriceAsString()

        dialogLayout
            .findViewById<Button>(R.id.bt_finish_buy)
            .setOnClickListener {
                val intent = Intent(this, ThankYouActivity::class.java)
                intent.putExtra(
                        Sneaker.KEY,
                        sneaker)
                startActivity(intent)
                finish()
            }

        builder.create().show()
    }
}
