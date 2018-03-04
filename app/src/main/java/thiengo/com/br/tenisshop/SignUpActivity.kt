package thiengo.com.br.tenisshop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CompoundButton
import it.sephiroth.android.library.tooltip.Tooltip
import it.sephiroth.android.library.tooltip.Tooltip.AnimationBuilder
import it.sephiroth.android.library.tooltip.Tooltip.ClosePolicy
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.content_sign_up.*
import thiengo.com.br.tenisshop.domain.Util


class SignUpActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cb_terms_of_use.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(checkButton: CompoundButton?, status: Boolean) {
        bt_sign_up.isEnabled = status
    }

    fun helpText(view: View){
        Tooltip.make(this,
            Tooltip.Builder(1)
                .anchor(iv_help, Tooltip.Gravity.TOP)
                .closePolicy(
                    ClosePolicy()
                        .insidePolicy(true, false)
                        .outsidePolicy(true, false), 3000)
                .activateDelay(1000)
                .text(resources.getString(R.string.help_text))
                .maxWidth( Util.convertDpToPixel( resources,200F) )
                .withArrow(true)
                .withStyleId(R.style.ToolTipLayoutCustomStyle)
                .withOverlay(true)
                .floatingAnimation(AnimationBuilder.DEFAULT)
                .build()
        ).show()
    }
}
