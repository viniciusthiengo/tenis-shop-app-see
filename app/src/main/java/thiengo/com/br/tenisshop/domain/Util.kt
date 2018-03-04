package thiengo.com.br.tenisshop.domain

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import thiengo.com.br.tenisshop.R
import kotlin.math.ceil


class Util {
    companion object {
        fun setImageViewBgColor(context: Context, imageView: ImageView) {
            val bitmap = (imageView.getDrawable() as BitmapDrawable).bitmap
            /*
             * Estamos obtendo a cor do pixel na coordenada (2.1dp, 2.1dp),
             * pois sabemos que nesta coordenada se inicia a verdadeira
             * cor de borda da imagem original, isso tendo em mente
             * que o shape image_view_sneaker.xml tem um padding de
             * 2dp e um background branco e não queremos obtê-lo para a
             * definição dinâmica de imagem de background.
             * */
            val initPixelPosition = Util.convertDpToPixel(context.resources, 2.1F)
            val pixel = bitmap.getPixel(initPixelPosition, initPixelPosition)

            val bgShape = imageView.background.current as GradientDrawable
            bgShape.setColor(pixel)
        }

        fun convertDpToPixel(resources: Resources, dp: Float): Int {
            val metrics = resources.getDisplayMetrics()
            val px = dp * (metrics.densityDpi / 160f)
            return Math.round(px)
        }

        fun setGenre(sneaker: Sneaker, male: ImageView, female: ImageView){
            male.visibility =
                if( sneaker.isForMale )
                    View.VISIBLE
                else
                    View.GONE

            female.visibility =
                if( sneaker.isForFemale )
                    View.VISIBLE
                else
                    View.GONE
        }

        fun setStar(parent: View, starResourceId: Int, position: Int, rating: Int){
            val ivStar = parent.findViewById(starResourceId) as ImageView

            ivStar.setImageResource(
                    if( position <= rating )
                        R.drawable.ic_star_black_18dp
                    else
                        R.drawable.ic_star_border_white_18dp
            )
        }
    }
}