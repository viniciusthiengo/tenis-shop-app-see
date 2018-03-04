package thiengo.com.br.tenisshop.data

import android.content.Context
import thiengo.com.br.tenisshop.R
import thiengo.com.br.tenisshop.domain.Brand
import thiengo.com.br.tenisshop.domain.Rating
import thiengo.com.br.tenisshop.domain.Sneaker


class Database {
    companion object {

        /*
         * Retorna os dados de teste do aplicativo, dados
         * simulados ou dados mock
         * */
        fun getSneakers() =
            listOf<Sneaker>(
                Sneaker(
                    R.drawable.shoes_01_a,
                    intArrayOf(R.drawable.shoes_01_b, R.drawable.shoes_01_c, R.drawable.shoes_01_d),
                    "Fresh Foam Cruz",
                    Brand(R.drawable.ic_new_balance, "New Balance"),
                    true,
                    true,
                    Rating(42, 5),
                    499.90
                ),
                Sneaker(
                    R.drawable.shoes_02_a,
                    intArrayOf(R.drawable.shoes_02_b, R.drawable.shoes_02_c, R.drawable.shoes_02_d),
                    "Epic React Flyknit",
                    Brand(R.drawable.ic_nike, "Nike"),
                    true,
                    false,
                    Rating(91, 5),
                    699.90
                ),
                Sneaker(
                    R.drawable.shoes_03_a,
                    intArrayOf(R.drawable.shoes_03_b, R.drawable.shoes_03_c, R.drawable.shoes_03_d),
                    "Supernova",
                    Brand(R.drawable.ic_adidas, "Adidas"),
                    true,
                    false,
                    Rating(29, 3),
                    599.99
                ),
                Sneaker(
                    R.drawable.shoes_04_a,
                    intArrayOf(R.drawable.shoes_04_b, R.drawable.shoes_04_c, R.drawable.shoes_04_d),
                    "GEL-Kenun",
                    Brand(R.drawable.ic_asics, "Asics"),
                    true,
                    false,
                    Rating(84, 4),
                    699.90
                ),
                Sneaker(
                    R.drawable.shoes_05_a,
                    intArrayOf(R.drawable.shoes_05_b, R.drawable.shoes_05_c, R.drawable.shoes_05_d),
                    "Charged Bandit 3",
                    Brand(R.drawable.ic_under_armour, "UnderArmour"),
                    false,
                    true,
                    Rating(19, 5),
                    349.90
                ),
                Sneaker(
                    R.drawable.shoes_06_a,
                    intArrayOf(R.drawable.shoes_06_b, R.drawable.shoes_06_c, R.drawable.shoes_06_d),
                    "Wave Sky",
                    Brand(R.drawable.ic_mizuno, "Mizuno"),
                    true,
                    true,
                    Rating(42, 5),
                    749.99
                )
            )

        /*
         * Ambos os métodos abaixo trabalham com o SharedPreferences
         * para que seja possível simular que o usuário atual está ou
         * não logado ao aplicativo.
         * */
        fun setLoggedUser(context: Context, value: Boolean){
            context
                .getSharedPreferences("PREF_DATA", Context.MODE_PRIVATE)
                .edit().putBoolean("is-logged", value)
                .apply()
        }
        fun isUserLogged(context: Context): Boolean{
            return context
                .getSharedPreferences("PREF_DATA", Context.MODE_PRIVATE)
                .getBoolean("is-logged", false)
        }
    }
}