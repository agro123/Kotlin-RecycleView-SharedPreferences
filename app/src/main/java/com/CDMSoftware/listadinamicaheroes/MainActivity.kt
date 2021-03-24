package com.CDMSoftware.listadinamicaheroes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.CDMSoftware.listadinamicaheroes.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),OnClickListener {

    private lateinit var heroeAdapter : HeroeAdapter
    private lateinit var linearLayoutManager : RecyclerView.LayoutManager
    private lateinit var viewBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean(getString(R.string.spFirstTime),true)
        if(isFirstTime){
            val dialogView = layoutInflater.inflate(R.layout.dialog_register,null)
            MaterialAlertDialogBuilder(this)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(R.string.confirm_Dialog,{dialogInterface,i_->
                    val userName = dialogView.findViewById<EditText>(R.id.etUsername).text.toString()
                    with(preferences.edit()){
                        putBoolean(getString(R.string.spFirstTime), false)
                        putString(getString(R.string.spUserName),userName).apply()
                    }
                })
                .show()

        }else {
           val userName = preferences.getString(getString(R.string.spFirstTime)
                ,getString(R.string.userDefault))
            Toast.makeText(this, "Bienvenido $userName  :D", Toast.LENGTH_SHORT).show()
        }

        heroeAdapter = HeroeAdapter(getHeroes(),this)
        linearLayoutManager = LinearLayoutManager(this)

        viewBinding.rvListaHeroes.apply {
            layoutManager = linearLayoutManager
            adapter = heroeAdapter
        }
    }

    private fun getHeroes() : MutableList<Heroe> {
        val heroes = mutableListOf<Heroe>()

        val ironMan = Heroe("Iron Man", "Tony Stark", "https://i.blogs.es/7ccbec/iron-man/1024_2000.jpg")
        val capitanAmerica = Heroe("Captain America", "Steve Rogers","https://xoandelugo.org/wp-content/uploads/2018/06/capitan-america.jpg")
        val blackWidow =  Heroe("Black Widow", "Natacha Romanoff","https://i.blogs.es/2f4e57/widow-min/450_1000.jpg")
        val scarletWitch = Heroe("Scarlet Witch","Wanda Maximoff","https://dam.smashmexico.com.mx/wp-content/uploads/2020/12/scarlet-witch-arte-conceptual-mcu-andy-park-cover.jpg")
        val  falcon = Heroe("Falcon","Samuel Wilson","https://www.enter.co/wp-content/uploads/2016/04/falcon-1024x768.jpg")

        heroes.add(ironMan)
        heroes.add(capitanAmerica)
        heroes.add(blackWidow)
        heroes.add(scarletWitch)
        heroes.add(falcon)

        return heroes
    }

    override fun onClick(heroe: Heroe) {
       Toast.makeText(this,heroe.getFullName(),Toast.LENGTH_SHORT).show()
    }
}