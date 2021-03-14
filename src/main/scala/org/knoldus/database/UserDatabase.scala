package org.knoldus.database

import org.knoldus.dao.Dao
import org.knoldus.model.User

import java.util.UUID
import scala.collection.mutable.ListBuffer

// Implementing userRepository class having methods to perform the CRUD operation
class UserDatabase extends Dao[User]{
  private val userList:ListBuffer[User] = ListBuffer.empty[User]
  override def create(user: User): Option[UUID] = {
    val userID = UUID.randomUUID()
    user match {
        case User(_, _, _, _) => userList.append(user.copy(id = Some(userID))); userList.last.id
        case User(_, _, _, _)=> null
    }
  }

  // Method to get user based on its id
  override def getByID(id: Option[UUID]): User = {
    val filteredList = userList.filter(user => {user.id == id})
    if(filteredList.isEmpty) throw  new NoSuchElementException("User does not exist")
    else filteredList.head
  }

  override def update(id: Option[UUID], updatedUser: User): Unit = {
    val indexOfUser = userList.indexOf(getByID(id))
    userList.update(indexOfUser,updatedUser)
  }

  override def delete(id: Option[UUID]): Unit = {
    val indexOfUser = userList.indexOf(getByID(id))
    userList.remove(indexOfUser)
  }

  override def getAll: List[User] = userList.toList
}