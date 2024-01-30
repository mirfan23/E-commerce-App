package com.catnip.core.base

import org.koin.core.module.Module

interface BaseModules {
    fun getModules() : List<Module>
}