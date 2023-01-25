package com.apps.nabungemas.ui.navigation

import com.apps.nabungemas.R

sealed class NavItem(val icon:Int, val route:String,val title:Int){
    object Home:NavItem(icon = R.drawable.ic_home, route = "home", title = R.string.home)
    object Transaction:NavItem(icon = R.drawable.ic_transaction, route = "transaction", title = R.string.transaction)
    object Graph:NavItem(icon = R.drawable.ic_chart, route = "graph", title = R.string.graph)
    object Saving:NavItem(icon = R.drawable.ic_wallet, route = "saving", title = R.string.saving)
    object About:NavItem(icon = R.drawable.ic_about, route = "about", title = R.string.about)
    object AddTransaction:NavItem(icon = 0, route = "add_transaction", title = R.string.add_transaction)
    object AddSaving:NavItem(icon = 0, route = "add_saving", title = R.string.add_saving)
}
