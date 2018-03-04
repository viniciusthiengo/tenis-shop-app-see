package thiengo.com.br.tenisshop.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import thiengo.com.br.tenisshop.R
import thiengo.com.br.tenisshop.SneakerDetailsActivity
import thiengo.com.br.tenisshop.domain.Sneaker
import android.graphics.drawable.GradientDrawable
import android.support.v7.graphics.Palette
import android.graphics.Bitmap
import android.R.attr.y
import android.R.attr.x
import android.graphics.drawable.BitmapDrawable
import thiengo.com.br.tenisshop.domain.Util


class SneakersAdapter(
        private val context: Context,
        private val sneakers: List<Sneaker>) :
        RecyclerView.Adapter<SneakersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int ) : SneakersAdapter.ViewHolder {

        val v = LayoutInflater
                .from(context)
                .inflate(R.layout.sneaker, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDados(sneakers[position])
    }

    override fun getItemCount(): Int {
        return sneakers.size
    }

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        var ivSneaker: ImageView
        var ivSneakerGallery01: ImageView
        var ivSneakerGallery02: ImageView
        var ivSneakerGallery03: ImageView
        var tvModel: TextView
        var ivBrand: ImageView
        var ivGenreMale: ImageView
        var ivGenreFemale: ImageView
        var tvPriceParcels: TextView
        var tvPrice: TextView

        init {
            itemView.setOnClickListener(this)

            ivSneaker = itemView.findViewById(R.id.iv_sneaker)
            ivSneakerGallery01 = itemView.findViewById(R.id.iv_sneaker_gallery_01)
            ivSneakerGallery02 = itemView.findViewById(R.id.iv_sneaker_gallery_02)
            ivSneakerGallery03 = itemView.findViewById(R.id.iv_sneaker_gallery_03)
            tvModel = itemView.findViewById(R.id.tv_model)
            ivBrand = itemView.findViewById(R.id.iv_brand)
            ivGenreMale = itemView.findViewById(R.id.iv_genre_male)
            ivGenreFemale = itemView.findViewById(R.id.iv_genre_female)
            tvPriceParcels = itemView.findViewById(R.id.tv_price_parcels)
            tvPrice = itemView.findViewById(R.id.tv_price)
        }

        fun setDados(sneaker: Sneaker) {
            setGallery(sneaker)

            tvModel.text = sneaker.model

            ivBrand.setImageResource( sneaker.brand.imageResource )
            ivBrand.contentDescription = sneaker.brand.name

            Util.setGenre(sneaker, ivGenreMale, ivGenreFemale)
            setRating(sneaker)
            setPrice(sneaker)
        }

        private fun setGallery(sneaker: Sneaker){
            ivSneaker.setImageResource( sneaker.imageResource )
            ivSneaker.contentDescription = "Tênis ${sneaker.model}"
            ivSneakerGallery01.setImageResource( sneaker.album[0] )
            ivSneakerGallery02.setImageResource( sneaker.album[1] )
            ivSneakerGallery03.setImageResource( sneaker.album[2] )

            Util.setImageViewBgColor(context, ivSneaker)
            Util.setImageViewBgColor(context, ivSneakerGallery01)
            Util.setImageViewBgColor(context, ivSneakerGallery02)
            Util.setImageViewBgColor(context, ivSneakerGallery03)
        }

        private fun setRating(sneaker: Sneaker){
            val tvRatingAmount = itemView.findViewById(R.id.tv_rating_amount) as TextView
            tvRatingAmount.text = "(${sneaker.rating.amount})"

            Util.setStar(itemView, R.id.iv_rating_star_01, 1, sneaker.rating.stars)
            Util.setStar(itemView, R.id.iv_rating_star_02, 2, sneaker.rating.stars)
            Util.setStar(itemView, R.id.iv_rating_star_03, 3, sneaker.rating.stars)
            Util.setStar(itemView, R.id.iv_rating_star_04, 4, sneaker.rating.stars)
            Util.setStar(itemView, R.id.iv_rating_star_05, 5, sneaker.rating.stars)
        }

        private fun setPrice(sneaker: Sneaker){
            tvPriceParcels.text = sneaker.getPriceParcelsAsString()
            tvPrice.text = sneaker.getPriceAsString()
        }

        override fun onClick(view: View?) {
            val intent = Intent(context, SneakerDetailsActivity::class.java)
            intent.putExtra(Sneaker.KEY, sneakers[adapterPosition])
            context.startActivity(intent)
        }
    }
}