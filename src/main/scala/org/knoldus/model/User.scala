package org.knoldus.model

import java.util.UUID
// creating a user class
case class User(id:Option[UUID],username:String, password:String, _type:Type )