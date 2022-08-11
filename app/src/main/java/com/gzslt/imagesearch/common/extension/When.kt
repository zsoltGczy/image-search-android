package com.gzslt.imagesearch.common.extension

/*
* A dummy extension to use on a `when` statement to make it an expression
* and guarantee that its branches perform exhaustive checks.
*/

public inline val <T> T.exhaustive: T
    get() = this
