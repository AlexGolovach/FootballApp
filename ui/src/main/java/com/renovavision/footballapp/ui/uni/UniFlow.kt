package com.renovavision.footballapp.ui.uni

interface Dispatchable // view send it to view model
interface Action : Dispatchable
interface AsyncAction : Dispatchable
typealias Dispatch = (dispatchable: Dispatchable) -> Unit