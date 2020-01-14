@file:Suppress("DEPRECATION")

package com.nickjgski.splatoongear


import android.os.Bundle
import android.util.Log
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
class WeaponFragment : Fragment() {

    private var sortBy = "Name"
    private var filterBy = "None"
    private val adapterName = WeaponListAdapter()
    private val adapterSub = WeaponListAdapter()
    private val adapterSpecial = WeaponListAdapter()
    private val adapterClass = WeaponListAdapter()
    private var model: GearViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_weapon, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.weaponList)
        recyclerView.adapter = adapterName
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        model = activity?.let { ViewModelProviders.of(it).get(GearViewModel::class.java)}

        model?.allWeapons?.observe(
            this,
            Observer<List<Weapon>>{weapons ->
                weapons?.let {
                    adapterName.setWeapons(it)
                }
            }
        )

        val sortSpinner: Spinner = view.findViewById(R.id.wSortOptions)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.weapon_sort_options,
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
                (recyclerView.adapter as WeaponListAdapter).modifyList()
            }
        }

        val filterSpinner: Spinner = view.findViewById(R.id.wFilterOptions)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.weapon_filter_options,
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
                (recyclerView.adapter as WeaponListAdapter).modifyList()
            }
        }

        view.findViewById<Button>(R.id.wheadgear).setOnClickListener {
            view.findNavController().navigate(R.id.action_weaponFragment_to_headgearFragment)
        }

        view.findViewById<Button>(R.id.wclothing).setOnClickListener {
            view.findNavController().navigate(R.id.action_weaponFragment_to_clothingFragment)
        }
        view.findViewById<Button>(R.id.wshoes).setOnClickListener {
            view.findNavController().navigate(R.id.action_weaponFragment_to_shoesFragment)
        }

        return view;
    }

    inner class WeaponListAdapter():
        RecyclerView.Adapter<WeaponListAdapter.WeaponViewHolder>(){
        private var weapons = emptyList<Weapon>()
        private var weaponsBackup = emptyList<Weapon>()

        internal fun setWeapons(weapons: List<Weapon>) {
            weaponsBackup = weapons
            this.weapons = weapons
            notifyDataSetChanged()
        }

        fun restoreList() {
            weapons = weaponsBackup;
        }

        fun modifyList() {
            restoreList()
            when(sortBy) {
                "Name" -> weapons = weapons.sortedBy { it.name }
                "Sub" -> weapons = weapons.sortedBy { it.sub }
                "Special" -> weapons = weapons.sortedBy { it.special }
                "Class" -> weapons = weapons.sortedBy { it.type }
            }
            if(filterBy != "None") {
                weapons = weapons.filter { it.type == filterBy }
            }
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return weapons.size
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {


            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)
            return WeaponViewHolder(v)
        }

        override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {


            //holder.bindItems(movieList[position])

            Glide.with(this@WeaponFragment).load(resources.getString(R.string.picture_base_url)+weapons[position].imagePath)
                .apply( RequestOptions().override(128, 128)).into(holder.view.findViewById(R.id.poster))
            holder.view.findViewById<TextView>(R.id.title).text=weapons[position].name
            holder.view.findViewById<TextView>(R.id.rating).text="Sub: ${weapons[position].sub}\n" +
                    "Special: ${weapons[position].special}\nClass: ${weapons[position].type}"

        }


        inner class WeaponViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
            override fun onClick(view: View?){
                if (view != null) {


                }
            }
        }
    }

}
