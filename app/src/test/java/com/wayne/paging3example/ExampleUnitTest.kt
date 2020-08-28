package com.wayne.paging3example

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val user1 = User("name1", arrayListOf("book1_1", "book1_2"))
        val user2 = User("name2", arrayListOf("book2_1", "book2_2"))
        val list = arrayListOf(user1, user2)

//        Observable.fromIterable(list)
//            .map {
//                println("book map $it")
//                return@map it.book
//            }.subscribe {
//                println("book map subscribe $it")
//            }
//        Observable.just(user1, user2)
//            .map {
//                return@map it.book
//            }.subscribe {
//                println("book just $it")
//            }
//        Observable.just(user1, user2)
//            .flatMap {
//                return@flatMap Observable.fromIterable(it.book)
//            }.subscribe {
//                println("name $it")
//            }
//        Observable.fromIterable(list)
//            .flatMap {
//                println("book flatMap $it")
//                return@flatMap Observable.fromIterable(it.book)
//            }.subscribe {
//                println("book flatMap subscribe $it")
//            }

//        Observable.just(user1, user2)
//            .map {
//                println("book map $it")
//                return@map it.book
//            }.subscribe {
//                println("book map subscribe $it")
//            }
//        Observable.just(user1, user2)
//            .flatMap {
//                println("book flatMap $it")
//                return@flatMap Observable.just(it.book)
//            }.subscribe {
//                println("book flatMap subscribe $it")
//            }

//        Observable.just("a", "b", "c")
//            .map {
//                println("book map $it")
//                return@map Observable.just(it)
//            }.subscribe {
//                println("book map subscribe $it")
//            }
        Observable.just(1, 2, 3)
            .flatMap {
                println("book flatMap -- 1 --- $it")
                return@flatMap Observable.just(arrayListOf(it))
            }.subscribe {
                println("book flatMap -- 2 --- $it")
            }

//        Observable.just("A", "B", "C")
//            .flatMap { i ->
//                Observable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS)
//                    .map { n -> "($n : $i)" }
//            }.subscribe {
//
//                println(it)
//            }


        Thread.sleep(5000)
        assertEquals(4, 2 + 2)
    }

    data class User (val name: String, val book: List<String>)
}