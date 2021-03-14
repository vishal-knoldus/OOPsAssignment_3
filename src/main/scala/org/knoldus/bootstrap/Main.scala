package org.knoldus.bootstrap

import org.knoldus.dao.Dao
import org.knoldus.database.UserDatabase
import org.knoldus.model.{Type, User}
import org.knoldus.service.UserService

object  Main {

  def main(args: Array[String]): Unit = {
    val userDB:Dao[User] = new UserDatabase()
    val userService = new UserService(userDB)

    // Creating customer type user
    val user1 = User(None,"vishal","vg123",Type.Customer)
    val user1_id = userService.createUser(user1)

    //Creating Admin type user
    val user2 = User(None,"Abhay","ag123",Type.Admin)
    val user2_id = userService.createUser(user2)

    //Listing all user
    println(userService.getAllUser)

    // Update username of admin
    val updated_user2 = userService.getUserByID(user2_id).copy(username = "Nehal")
    userService.updateUser(user2_id,updated_user2)

    //Getting updated Admin
    println(userService.getUserByID(user2_id))

    //Delete customer
    userService.deleteUser(user1_id)

    //Trying to get the deleted Customer(i.e. user1) from the database. So it will throw a NoSuchElementException. To check it, uncomment the below line of code.
    //userService.getUserByID(user1_id)

  }

}
