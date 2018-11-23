package com.chomper.watermap.di.bean

open class BaseResponseEntity(var code: Int, var message: String)

class GeneralResponse<T>(code: Int, message: String, var data: T) : BaseResponseEntity(code, message)
