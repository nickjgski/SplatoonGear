@file:Suppress("DEPRECATION")

package com.nickjgski.splatoongear


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * A simple [Fragment] subclass.
 */
class ClothingFragment : Fragment() {

    private var sortBy = "Name"
    private var filterBy = "None"
    private val adapter = WearableListAdapter()
    private var model: GearViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_clothing, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.clothingList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        model = activity?.let { ViewModelProviders.of(it).get(GearViewModel::class.java)}

        model?.allClothing?.observe(
            this,
            Observer<List<Wearable>>{headgear ->
                headgear?.let {
                    adapter.setWearables(it)
                }
            }
        )

        val sortSpinner: Spinner = view.findViewById(R.id.cSortOptions)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.wearable_sort_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sortSpinner.adapter = adapter
        }
        sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sortBy = parent?.getItemAtPosition(position).toString()
                (recyclerView.adapter as WearableListAdapter).modifyList()
            }
        }
        val filterSpinner: Spinner = view.findViewById(R.id.cFilterOptions)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.clothing_filter_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            filterSpinner.adapter = adapter
        }
        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterBy = parent?.getItemAtPosition(position).toString()
                (recyclerView.adapter as WearableListAdapter).modifyList()
            }
        }

        view.findViewById<Button>(R.id.cheadgear).setOnClickListener {
            view.findNavController().navigate(R.id.action_clothingFragment_to_headgearFragment)
        }

        view.findViewById<Button>(R.id.cweapons).setOnClickListener {
            view.findNavController().navigate(R.id.action_clothingFragment_to_weaponFragment)
        }

        view.findViewById<Button>(R.id.cshoes).setOnClickListener {
            view.findNavController().navigate(R.id.action_clothingFragment_to_shoesFragment)
        }

        return view;
    }

    inner class WearableListAdapter():
        RecyclerView.Adapter<WearableListAdapter.WeaponViewHolder>(){
        private var wearables = emptyList<Wearable>()
        private var wearablesBackup = emptyList<Wearable>()

        internal fun setWearables(wearables: List<Wearable>) {
            wearablesBackup = wearables
            this.wearables = wearables
            notifyDataSetChanged()
        }

        fun restoreList() {
            wearables = wearablesBackup;
        }

        fun modifyList() {
            restoreList()
            when(sortBy) {
                "Name" -> wearables = wearables.sortedBy { it.name }
                "Rarity" -> wearables = wearables.sortedBy { it.rarity }
                "Ability" -> wearables = wearables.sortedBy { it.ability }
            }
            if(filterBy != "None") {
                wearables = wearables.filter { it.ability == filterBy }
            }
            notifyDataSetChanged()
        }


        override fun getItemCount(): Int {

            return wearables.size
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {


            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)
            return WeaponViewHolder(v)
        }

        override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {


            //holder.bindItems(movieList[position])

            Glide.with(this@ClothingFragment).load(resources.getString(R.string.picture_base_url)+wearables[position].imagePath)
                .apply( RequestOptions().override(128, 128)).into(holder.view.findViewById(R.id.poster))
            holder.view.findViewById<TextView>(R.id.title).text=wearables[position].name
            holder.view.findViewById<TextView>(R.id.rating).text="Ability: ${wearables[position].ability}\n" +
                    "Rarity: ${wearables[position].rarity}"

        }


        inner class WeaponViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
            override fun onClick(view: View?){
                if (view != null) {


                }
            }
        }
    }

}
