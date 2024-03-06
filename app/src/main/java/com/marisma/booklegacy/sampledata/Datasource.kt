/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marisma.booklegacy.sampledata

class Datasource {
    companion object {
        var favBookList = mutableListOf<Book>()
        val bookList = mutableListOf<Book>()
        public fun rellenaFAV(){
        favBookList.clear()
        bookList.forEach{
            if(it.fav){

                favBookList.add(it)
            }
        }
        }
        fun fetchBookFromApi() {
            val identificadores = arrayOf("OL26451891M","OL50716172M","OL27448W","OL35632564M","OL17076473W","OL50533834M","OL32008012W")
            val bookApi = BookApi()

            for (libro in identificadores) {


                bookApi.getBookById(libro) { book ->
                    if (book != null) {
                        Datasource.bookList.add(book)
                    }
                }


            }



        }

    }
    }
