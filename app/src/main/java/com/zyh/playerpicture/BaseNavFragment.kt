package com.zyh.playerpicture

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

/**
 *author: luqihua
 *date:2021/3/4
 *description:
 **/
open class BaseNavFragment : Fragment() {
    fun nav() = NavHostFragment.findNavController(this)
}