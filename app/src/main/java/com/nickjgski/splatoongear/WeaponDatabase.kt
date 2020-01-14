package com.nickjgski.splatoongear

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Weapon::class], version = 1, exportSchema = false)
abstract class WeaponDatabase: RoomDatabase() {

    abstract fun weaponDao(): WeaponDao

    companion object {

        @Volatile
        private var INSTANCE: WeaponDatabase? = null

        fun getInstance(context: Context): WeaponDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WeaponDatabase::class.java, "Weapon.db"
            )
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        Thread( Runnable {
                            getInstance(context).weaponDao().insertAll(PREPOPULATE_DATA)
                        }).start()
                    }
                })
                .build()

        val PREPOPULATE_DATA: List<Weapon> = listOf(
            Weapon(".52 Gal", "Point Sensor", "Baller", "Shooter", "6/66/S2_Weapon_Main_.52_Gal.png"),
            Weapon(".52 Gal Deco", "Curling Bomb", "String Ray", "Shooter", "1/17/S2_Weapon_Main_.52_Gal_Deco.png"),
            Weapon(".96 Gal", "Sprinkler", "Ink Armor", "Shooter", "3/39/S2_Weapon_Main_.96_Gal.png"),
            Weapon(".96 Gal Deco", "Splash Wall", "Splashdown", "Shooter", "c/c3/S2_Weapon_Main_.96_Gal_Deco.png"),
            Weapon("Aerospray MG", "Suction Bomb", "Curling-Bomb Launcher", "Shooter", "a/a1/S2_Weapon_Main_Aerospray_MG.png"),
            Weapon("Aerospray PG", "Burst Bomb", "Booyah Bomb", "Shooter", "3/35/S2_Weapon_Main_Aerospray_PG.png"),
            Weapon("Aerospray RG", "Sprinkler", "Baller", "Shooter", "5/5e/S2_Weapon_Main_Aerospray_RG.png"),
            Weapon("Ballpoint Splatling", "Toxic Mist", "Inkjet", "Splatling", "8/8c/S2_Weapon_Main_Ballpoint_Splatling.png"),
            Weapon("Ballpoint Splatling Nouveau", "Squid Beakon", "Ink Storm", "Splatling", "b/b4/S2_Weapon_Main_Ballpoint_Splatling_Nouveau.png"),
            Weapon("Bamboozler 14 Mk I", "Curling Bomb", "Tenta Missiles", "Charger", "5/50/S2_Weapon_Main_Bamboozler_14_Mk_I.png"),
            Weapon("Bamboozler 14 Mk II", "Toxic Mist", "Burst-Bomb Launcher", "Charger", "f/f4/S2_Weapon_Main_Bamboozler_14_Mk_II.png"),
            Weapon("Bamboozler 14 Mk III", "Fizzy Bomb", "Bubble Blower", "Charger", "c/ca/S2_Weapon_Main_Bamboozler_14_Mk_III.png"),
            Weapon("Blaster", "Toxic Mist", "Splashdown", "Shooter", "c/cc/S2_Weapon_Main_Blaster.png"),
            Weapon("Bloblobber", "Splash Wall", "Ink Storm", "Slosher", "4/45/S2_Weapon_Main_Bloblobber.png"),
            Weapon("Bloblobber Deco", "Sprinkler", "Suction-Bomb Launcher", "Slosher", "1/19/S2_Weapon_Main_Bloblobber_Deco.png"),
            Weapon("Carbon Roller", "Autobomb", "Ink Storm", "Roller", "8/8a/S2_Weapon_Main_Carbon_Roller.png"),
            Weapon("Carbon Roller Deco", "Burst Bomb", "Autobomb Launcher", "Roller", "4/49/S2_Weapon_Main_Carbon_Roller_Deco.png"),
            Weapon("Cherry H-3 Nozzlenose", "Splash Wall", "Bubble Blower", "Shooter", "2/24/S2_Weapon_Main_Cherry_H-3_Nozzlenose.png"),
            Weapon("Clash Blaster", "Splat Bomb", "Sting Ray", "Shooter", "2/21/S2_Weapon_Main_Clash_Blaster.png"),
            Weapon("Clash Blaster Neo", "Curling Bomb", "Tenta Missiles", "Shooter", "f/fa/S2_Weapon_Main_Clash_Blaster_Neo.png"),
            Weapon("Classic Squiffer", "Point Sensor", "Ink Armor", "Charger", "6/6f/S2_Weapon_Main_Classic_Squiffer.png"),
            Weapon("Clear Dapple Dualies", "Torpedo", "Splashdown", "Dualie", "1/15/S2_Weapon_Main_Clear_Dapple_Dualies.png"),
            Weapon("Custom Blaster", "Autobomb", "Inkjet", "Shooter", "0/0c/S2_Weapon_Main_Custom_Blaster.png"),
            Weapon("Custom Dualie Squelchers", "Splat Bomb", "Ink Storm", "Dualie", "1/15/S2_Weapon_Main_Custom_Dualie_Squelchers.png"),
            Weapon("Custom E-Liter 4K", "Squid Beakon", "Bubble Blower", "Charger", "a/af/S2_Weapon_Main_Custom_E-liter_4K.png"),
            Weapon("Custom E-Liter 4K Scope", "Squid Beakon", "Bubble Blower", "Charger", "1/14/S2_Weapon_Main_Custom_E-liter_4K_Scope.png"),
            Weapon("Custom Explosher", "Point Sensor", "Baller", "Slosher", "a/a4/S2_Weapon_Main_Custom_Explosher.png"),
            Weapon("Custom Goo Tuber", "Curling Bomb", "Inkjet", "Charger", "0/0a/S2_Weapon_Main_Custom_Goo_Tuber.png"),
            Weapon("Custom Hydra Splatling", "Ink Mine", "Ink Armor", "Splatling", "7/73/S2_Weapon_Main_Custom_Hydra_Splatling.png"),
            Weapon("Custom Jet Squelcher", "Burst Bomb", "Sting Ray", "Shooter", "4/4b/S2_Weapon_Main_Custom_Jet_Squelcher.png"),
            Weapon("Custom Range Blaster", "Curling Bomb", "Bubble Blower", "Shooter", "a/a7/S2_Weapon_Main_Custom_Range_Blaster.png"),
            Weapon("Custom Splattershot Jr.", "Autobomb", "Ink Storm", "Shooter", "6/6c/S2_Weapon_Main_Custom_Splattershot_Jr..png"),
            Weapon("Dapple Dualies", "Squid Beakon", "Suction-Bomb Launcher", "Dualie", "6/69/S2_Weapon_Main_Dapple_Dualies.png"),
            Weapon("Dapple Dualies Nouveau", "Toxic Mist", "Ink Storm", "Dualie", "2/20/S2_Weapon_Main_Dapple_Dualies_Nouveau.png"),
            Weapon("Dark Tetra Dualies", "Autobomb", "Splashdown", "Dualie", "8/8d/S2_Weapon_Main_Dark_Tetra_Dualies.png"),
            Weapon("Dualie Squelchers", "Point Sensor", "Tenta Missiles", "Dualie", "3/33/S2_Weapon_Main_Dualie_Squelchers.png"),
            Weapon("Dynamo Roller", "Ink Mine", "Sting Ray", "Roller", "2/24/S2_Weapon_Main_Dynamo_Roller.png"),
            Weapon("E-Liter 4K", "Ink Mine", "Ink Storm", "Charger", "d/db/S2_Weapon_Main_E-liter_4K.png"),
            Weapon("E-Liter 4K Scope", "Ink Mine", "Ink Storm", "Charger", "0/01/S2_Weapon_Main_E-liter_4K_Scope.png"),
            Weapon("Enberry Splat Dualies", "Curling Bomb", "Inkjet", "Dualie", "6/69/S2_Weapon_Main_Enperry_Splat_Dualies.png"),
            Weapon("Explosher", "Sprinkler", "Bubble Blower", "Slosher", "1/13/S2_Weapon_Main_Explosher.png"),
            Weapon("Firefin Splat Charger", "Splash Wall", "Suction-Bomb Launcher", "Charger", "b/bb/S2_Weapon_Main_Firefin_Splat_Charger.png"),
            Weapon("Firefin Splatterscope", "Splash Wall", "Suction-Bomb Launcher", "Charger", "d/d6/S2_Weapon_Main_Firefin_Splatterscope.png"),
            Weapon("Flingza Roller", "Splash Wall", "Splat-Bomb Launcher", "Roller", "6/6c/S2_Weapon_Main_Flingza_Roller.png"),
            Weapon("Foil Flingza Roller", "Suction Bomb", "Tenta Missiles", "Roller", "f/ff/S2_Weapon_Main_Foil_Flingza_Roller.png"),
            Weapon("Foil Squeezer", "Splat Bomb", "Bubble Blower", "Shooter", "e/e7/S2_Weapon_Main_Foil_Squeezer.png"),
            Weapon("Forge Splattershot Pro", "Suction Bomb", "Bubble Blower", "Shooter", "a/a7/S2_Weapon_Main_Forge_Splattershot_Pro.png"),
            Weapon("Fresh Squiffer", "Suction Bomb", "Inkjet", "Charger", "c/c1/S2_Weapon_Main_Fresh_Squiffer.png"),
            Weapon("Glooga Dualies", "Ink Mine", "Inkjet", "Dualie", "6/68/S2_Weapon_Main_Glooga_Dualies.png"),
            Weapon("Glooga Dualies Deco", "Splash Wall", "Baller", "Dualie", "8/82/S2_Weapon_Main_Glooga_Dualies_Deco.png"),
            Weapon("Gold Dynamo Roller", "Splat Bomb", "Ink Armor", "Roller", "2/26/S2_Weapon_Main_Gold_Dynamo_Roller.png"),
            Weapon("Goo Tuber", "Suction Bomb", "Splashdown", "Charger", "0/06/S2_Weapon_Main_Goo_Tuber.png"),
            Weapon("Grim Range Blaster", "Burst Bomb", "Tenta Missiles", "Shooter", "0/08/S2_Weapon_Main_Grim_Range_Blaster.png"),
            Weapon("H-3 Nozzlenose", "Point Sensor", "Tenta Missiles", "Shooter", "c/c5/S2_Weapon_Main_H-3_Nozzlenose.png"),
            Weapon("H-3 Nozzlenose D", "Suction Bomb", "Ink Armor", "Shooter", "0/01/S2_Weapon_Main_H-3_Nozzlenose_D.png"),
            Weapon("Heavy Splatling", "Sprinkler", "Sting Ray", "Splatling", "2/2f/S2_Weapon_Main_Heavy_Splatling.png"),
            Weapon("Heavy Splatling Deco", "Splash Wall", "Bubble Blower", "Splatling", "9/9a/S2_Weapon_Main_Heavy_Splatling_Deco.png"),
            Weapon("Heavy Splatling Remix", "Point Sensor", "Booyah Bomb", "Splatling", "0/05/S2_Weapon_Main_Heavy_Splatling_Remix.png"),
            Weapon("Hero Blaster Replica", "Toxic Mist", "Splashdown", "Shooter", "f/f6/S2_Weapon_Main_Hero_Blaster_Replica.png"),
            Weapon("Hero Brella Replica", "Sprinkler", "Ink Storm", "Brella", "9/96/S2_Weapon_Main_Hero_Brella_Replica.png"),
            Weapon("Hero Charger Replica", "Splat Bomb", "Sting Ray", "Charger", "7/72/S2_Weapon_Main_Hero_Charger_Replica.png"),
            Weapon("Hero Dualie Replicas", "Burst Bomb", "Tenta Missiles", "Dualie", "4/4b/S2_Weapon_Main_Hero_Dualie_Replicas.png"),
            Weapon("Hero Roller Replica", "Curling Bomb", "Splashdown", "Roller", "1/1b/S2_Weapon_Main_Hero_Roller_Replica.png"),
            Weapon("Hero Shot Replica", "Burst Bomb", "Splashdown", "Shooter", "9/9c/S2_Weapon_Main_Hero_Shot_Replica.png"),
            Weapon("Hero Slosher Replica", "Suction Bomb", "Tenta Missiles", "Slosher", "4/42/S2_Weapon_Main_Hero_Slosher_Replica.png"),
            Weapon("Hero Splatling Replica", "Sprinkler", "Sting Ray", "Splatling", "a/a0/S2_Weapon_Main_Hero_Splatling_Replica.png"),
            Weapon("Herobrush Replica", "Autobomb", "Inkjet", "Roller", "4/4c/S2_Weapon_Main_Herobrush_Replica.png"),
            Weapon("Hydra Splatling", "Autobomb", "Splashdown", "Splatling", "6/69/S2_Weapon_Main_Hydra_Splatling.png"),
            Weapon("Inkbrush", "Splat Bomb", "Splashdown", "Roller", "c/cb/S2_Weapon_Main_Inkbrush.png"),
            Weapon("Inkbrush Nouveau", "Ink Mine", "Baller", "Roller", "e/ef/S2_Weapon_Main_Inkbrush_Nouveau.png"),
            Weapon("Jet Squelcher", "Toxic Mist", "Tenta Missiles", "Shooter", "9/91/S2_Weapon_Main_Jet_Squelcher.png"),
            Weapon("Kensa .52 Gal", "Splash Wall", "Booyah Bomb", "Shooter", "f/fa/S2_Weapon_Main_Kensa_.52_Gal.png"),
            Weapon("Kensa Charger", "Sprinkler", "Baller", "Charger", "e/ee/S2_Weapon_Main_Kensa_Charger.png"),
            Weapon("Kensa Dynamo Roller", "Sprinkler", "Booyah Bomb", "Roller", "6/6b/S2_Weapon_Main_Kensa_Dynamo_Roller.png"),
            Weapon("Kensa Glooga Dualies", "Fizzy Bomb", "Ink Armor", "Dualie", "1/1f/S2_Weapon_Main_Kensa_Glooga_Dualies.png"),
            Weapon("Kensa L-3 Nozzlenose", "Splash Wall", "Ultra Stamp", "Shooter", "d/d8/S2_Weapon_Main_Kensa_L-3_Nozzlenose.png"),
            Weapon("Kensa Luna Blaster", "Fizzy Bomb", "Ink Storm", "Shooter", "9/90/S2_Weapon_Main_Kensa_Luna_Blaster.png"),
            Weapon("Kensa Mini Splatling", "Toxic Mist", "Ultra Stamp", "Splatling", "c/c2/S2_Weapon_Main_Kensa_Mini_Splatling.png"),
            Weapon("Kensa Octobrush", "Suction Bomb", "Ultra Stamp", "Roller", "2/22/S2_Weapon_Main_Kensa_Octobrush.png"),
            Weapon("Kensa Rapid Blaster", "Torpedo", "Baller", "Shooter", "c/ca/S2_Weapon_Main_Kensa_Rapid_Blaster.png"),
            Weapon("Kensa Sloshing Machine", "Fizzy Bomb", "Splashdown", "Slosher", "c/cf/S2_Weapon_Main_Kensa_Sloshing_Machine.png"),
            Weapon("Kensa Splat Dualies", "Suction Bomb", "Baller", "Dualie", "a/a5/S2_Weapon_Main_Kensa_Splat_Dualies.png"),
            Weapon("Kensa Splat Roller", "Splat Bomb", "Bubble Blower", "Roller", "c/c2/S2_Weapon_Main_Kensa_Splat_Roller.png"),
            Weapon("Kensa Splatterscope", "Sprinkler", "Baller", "Charger", "e/e2/S2_Weapon_Main_Kensa_Splatterscope.png"),
            Weapon("Kensa Splattershot", "Suction Bomb", "Tenta Missiles", "Shooter", "8/8f/S2_Weapon_Main_Kensa_Splattershot.png"),
            Weapon("Kensa Splattershot Jr.", "Torpedo", "Bubble Blower", "Shooter", "e/eb/S2_Weapon_Main_Kensa_Splattershot_Jr..png"),
            Weapon("Kensa Splattershot Pro", "Splat Bomb", "Booyah Bomb", "Shooter", "3/38/S2_Weapon_Main_Kensa_Splattershot_Pro.png"),
            Weapon("Kensa Undercover Brella", "Torpedo", "Ink Armor", "Brella", "e/e9/S2_Weapon_Main_Kensa_Undercover_Brella.png"),
            Weapon("Krak-On Splat Roller", "Squid Beakon", "Baller", "Roller", "6/62/S2_Weapon_Main_Krak-On_Splat_Roller.png"),
            Weapon("L-3 Nozzlenose", "Curling Bomb", "Baller", "Shooter", "8/84/S2_Weapon_Main_L-3_Nozzlenose.png"),
            Weapon("L-3 Nozzlenose D", "Burst Bomb", "Inkjet", "Shooter", "0/0b/S2_Weapon_Main_L-3_Nozzlenose_D.png"),
            Weapon("Light Tetra Dualies", "Sprinkler", "Autobomb Launcher", "Dualie", "f/f1/S2_Weapon_Main_Light_Tetra_Dualies.png"),
            Weapon("Luna Blaster", "Splat Bomb", "Baller", "Shooter", "6/6d/S2_Weapon_Main_Luna_Blaster.png"),
            Weapon("Luna Blaster Neo", "Ink Mine", "Suction-Bomb Launcher", "Shooter", "7/7e/S2_Weapon_Main_Luna_Blaster_Neo.png"),
            Weapon("Mini Splatling", "Burst Bomb", "Tenta Missiles", "Splatling", "7/76/S2_Weapon_Main_Mini_Splatling.png"),
            Weapon("N-Zap '83", "Sprinkler", "Ink Storm", "Shooter", "c/cb/S2_Weapon_Main_N-ZAP_%2783.png"),
            Weapon("N-Zap '85", "Suction Bomb", "Ink Armor", "Shooter", "e/e9/S2_Weapon_Main_N-ZAP_%2785.png"),
            Weapon("N-Zap '89", "Autobomb", "Tenta Missiles", "Shooter", "2/20/S2_Weapon_Main_N-ZAP_%2789.png"),
            Weapon("Nautilus 47", "Point Sensor", "Baller", "Splatling", "5/58/S2_Weapon_Main_Nautilus_47.png"),
            Weapon("Nautilus 79", "Suction Bomb", "Inkjet", "Splatling", "4/44/S2_Weapon_Main_Nautilus_79.png"),
            Weapon("Neo Splash-o-matic", "Burst Bomb", "Suction-Bomb Launcher", "Shooter", "4/46/S2_Weapon_Main_Neo_Splash-o-matic.png"),
            Weapon("Neo Sploosh-o-matic", "Squid Beakon", "Tenta Missiles", "Shooter", "e/e7/S2_Weapon_Main_Neo_Sploosh-o-matic.png"),
            Weapon("New Squiffer", "Autobomb", "Baller", "Charger", "2/21/S2_Weapon_Main_New_Squiffer.png"),
            Weapon("Octobrush", "Autobomb", "Inkjet", "Roller", "c/c3/S2_Weapon_Main_Octobrush.png"),
            Weapon("Octobrush Nouveau", "Squid Beakon", "Tenta Missiles", "Roller", "2/2a/S2_Weapon_Main_Octobrush_Nouveau.png"),
            Weapon("Octo Shot Replica", "Splat Bomb", "Inkjet", "Shooter", "e/e6/S2_Weapon_Main_Octo_Shot_Replica.png"),
            Weapon("Permanent Inkbrush", "Sprinkler", "Ink Armor", "Roller", "3/3a/S2_Weapon_Main_Permanent_Inkbrush.png"),
            Weapon("Range Blaster", "Suction Bomb", "Ink Storm", "Shooter", "3/3d/S2_Weapon_Main_Range_Blaster.png"),
            Weapon("Rapid Blaster", "Ink Mine", "Splat-Bomb Launcher", "Shooter", "1/1d/S2_Weapon_Main_Rapid_Blaster.png"),
            Weapon("Rapid Blaster Deco", "Suction Bomb", "Inkjet", "Shooter", "2/29/S2_Weapon_Main_Rapid_Blaster_Deco.png"),
            Weapon("Rapid Blaster Pro", "Toxic Mist", "Ink Storm", "Shooter", "e/e6/S2_Weapon_Main_Rapid_Blaster_Pro.png"),
            Weapon("Rapid Blaster Pro Deco", "Splash Wall", "Ink Armor", "Shooter", "5/59/S2_Weapon_Main_Rapid_Blaster_Pro_Deco.png"),
            Weapon("Slosher", "Suction Bomb", "Tenta Missiles", "Slosher", "a/aa/S2_Weapon_Main_Slosher.png"),
            Weapon("Slosher Deco", "Sprinkler", "Baller", "Slosher", "3/31/S2_Weapon_Main_Slosher_Deco.png"),
            Weapon("Sloshing Machine", "Autobomb", "Sting Ray", "Slosher", "e/ec/S2_Weapon_Main_Sloshing_Machine.png"),
            Weapon("Sloshing Machine Neo", "Point Sensor", "Suction-Bomb Launcher", "Slosher", "7/7d/S2_Weapon_Main_Sloshing_Machine_Neo.png"),
            Weapon("Soda Slosher", "Splat Bomb", "Burst-Bomb Launcher", "Slosher", "1/14/S2_Weapon_Main_Soda_Slosher.png"),
            Weapon("Sorella Brella", "Autobomb", "Splat-Bomb Launcher", "Brella", "c/c3/S2_Weapon_Main_Sorella_Brella.png"),
            Weapon("Splash-o-matic", "Toxic Mist", "Inkjet", "Shooter", "5/5b/S2_Weapon_Main_Splash-o-matic.png"),
            Weapon("Splat Brella", "Sprinkler", "Ink Storm", "Brella", "6/69/S2_Weapon_Main_Splat_Brella.png"),
            Weapon("Splat Charger", "Splat Bomb", "Sting Ray", "Charger", "e/eb/S2_Weapon_Main_Splat_Charger.png"),
            Weapon("Splat Dualies", "Burst Bomb", "Tenta Missiles", "Dualie", "d/de/S2_Weapon_Main_Splat_Dualies.png"),
            Weapon("Splat Roller", "Curling Bomb", "Splashdown", "Roller", "2/26/S2_Weapon_Main_Splat_Roller.png"),
            Weapon("Splatterscope", "Splat Bomb", "Sting Ray", "Charger", "a/ad/S2_Weapon_Main_Splatterscope.png"),
            Weapon("Splattershot", "Burst Bomb", "Splashdown", "Shooter", "6/60/S2_Weapon_Main_Splattershot.png"),
            Weapon("Splattershot Jr.", "Splat Bomb", "Ink Armor", "Shooter", "5/5e/S2_Weapon_Main_Splattershot_Jr..png"),
            Weapon("Splattershot Pro", "Point Sensor", "Ink Storm", "Shooter", "d/d1/S2_Weapon_Main_Splattershot_Pro.png"),
            Weapon("Sploosh-o-matic", "Curling Bomb", "Splashdown", "Shooter", "1/1a/S2_Weapon_Main_Sploosh-o-matic.png"),
            Weapon("Sploosh-o-matic 7", "Splat Bomb", "Ultra Stamp", "Shooter", "4/44/S2_Weapon_Main_Sploosh-o-matic_7.png"),
            Weapon("Squeezer", "Splash Wall", "Sting Ray", "Shooter", "f/f6/S2_Weapon_Main_Squeezer.png"),
            Weapon("Tenta Brella", "Squid Beakon", "Bubble Blower", "Brella", "f/f5/S2_Weapon_Main_Tenta_Brella.png"),
            Weapon("Tenta Camo Brella", "Ink Mine", "Ultra Stamp", "Brella", "d/d2/S2_Weapon_Main_Tenta_Camo_Brella.png"),
            Weapon("Tenta Sorella Brella", "Splash Wall", "Curling Bomb Launcher", "Brella", "8/8e/S2_Weapon_Main_Tenta_Sorella_Brella.png"),
            Weapon("Tentatek Splattershot", "Splat Bomb", "Inkjet", "Shooter", "b/b6/S2_Weapon_Main_Tentatek_Splattershot.png"),
            Weapon("Tri-Slosher", "Burst Bomb", "Ink Armor", "Slosher", "3/32/S2_Weapon_Main_Tri-Slosher.png"),
            Weapon("Tri-Slosher Nouveau", "Splat Bomb", "Ink Storm", "Slosher", "e/ed/S2_Weapon_Main_Tri-Slosher_Nouveau.png"),
            Weapon("Undercover Brella", "Ink Mine", "Splashdown", "Brella", "e/e6/S2_Weapon_Main_Undercover_Brella.png"),
            Weapon("Undercover Sorella Brella", "Splat Bomb", "Baller", "Brella", "9/93/S2_Weapon_Main_Undercover_Sorella_Brella.png"),
            Weapon("Zink Mini Splatling", "Curling Bomb", "Ink Storm", "Splatling", "c/ce/S2_Weapon_Main_Zink_Mini_Splatling.png")
        )
    }

}