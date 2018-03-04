package thiengo.com.br.tenisshop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import kotlinx.android.synthetic.main.activity_sneakers.*
import thiengo.com.br.tenisshop.adapter.SneakersAdapter
import thiengo.com.br.tenisshop.data.Database


class SneakersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sneakers)
    }

    override fun onStart() {
        super.onStart()

        rv_sneakers.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        rv_sneakers.layoutManager = layoutManager
        rv_sneakers.adapter = SneakersAdapter(this, Database.getSneakers())
    }

    /*
     * Somente para apresentarmos os itens de "buscar" e
     * "carrinho de compras"
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
}
