package com.nevereatalone.data.api

data class User(val uid: String = "", val username: String, val profilePicture: String,
                val taste: List<String>)