package com.example.volleypractice

class PostUser (
    var userId : Int,
    var id : Int,
    var title : String,
    var body : String
){
    override fun toString(): String {
        return "PostUser(userId=$userId, id=$id, title='$title', body='$body')"
    }
}