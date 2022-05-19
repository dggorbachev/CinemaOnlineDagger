package com.dggorbachev.cinemaonlinedagger.base

import com.dggorbachev.cinemaonlinedagger.base.functional.Either
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

inline fun <reified T> genericType() = object : TypeToken<T>() {}.type