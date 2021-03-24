package com.CDMSoftware.listadinamicaheroes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.CDMSoftware.listadinamicaheroes.databinding.ItemListaBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class HeroeAdapter (private val dataSetHeroes : List<Heroe>, private val listener: OnClickListener)
    : RecyclerView.Adapter<HeroeAdapter.ViewHolder>(){
           //lateinit garantizo que ese objeto va existir
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val viewBinding = ItemListaBinding.bind(view)
        fun setListener(heroe: Heroe){
            viewBinding.root.setOnClickListener{
                listener.onClick(heroe)
            }
        }
    }
                                   //representa el RV
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {//Crea dinamicamente el item

        context = parent.context
                                                        //elemento         | RV  |le digo que es una rama y no la raiz
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =  dataSetHeroes.size //conteo de elementos que se van a almacenar

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //Llena con la informacion
        val heroe = dataSetHeroes.get(position)

        // trabajar con los atributos de un elemento
        with(holder){
            setListener(heroe)
            viewBinding.tvName.text = heroe.name
            viewBinding.tvAlterEgo.text = heroe.alterEgo

            Glide.with(context).load(heroe.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .circleCrop()
                    .into(viewBinding.ivImageHeroe)
        }
    }
}