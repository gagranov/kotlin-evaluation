package com.emc.symmwin.puzzle

import kotlin.js.Date

actual fun getSystemTimeInMillis() = Date().getMilliseconds().toLong()


