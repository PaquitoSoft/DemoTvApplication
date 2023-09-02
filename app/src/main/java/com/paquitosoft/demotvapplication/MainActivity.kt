package com.paquitosoft.demotvapplication

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph

class MainActivity : FragmentActivity() {

    private lateinit var newGraph: NavGraph
    private lateinit var navController: NavController
    private lateinit var viewModel: DeepLinkViewModel
    private lateinit var castHelper: CastHelper
}