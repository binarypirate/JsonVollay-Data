package com.example.volleypractice

class User(
     var id: Int,
     var name: String,
     var username: String,
     var email: String,
     var address: Address,
     var phone: String,
     var website: String,
     var company: Company
) {
     override fun toString(): String {
          return "User(id=$id, name='$name', username='$username', email='$email', address=$address, phone='$phone', website='$website', company=$company)"
     }
}

class Company(
     var name: String,
     var catchPhrase: String,
     var bs: String
) {
     override fun toString(): String {
          return "Company(name='$name', catchPhrase='$catchPhrase', bs='$bs')"
     }
}

class Address(
     var street: String,
     var suite: String,
     var city: String,
     var zipcode: String,
     var geo: Geo
) {
     override fun toString(): String {
          return "Address(street='$street', suite='$suite', city='$city', zipcode='$zipcode', geo=$geo)"
     }
}

class Geo(var lat: String, var lng: String) {
     override fun toString(): String {
          return "Geo(lat='$lat', lng='$lng')"
     }
}