package org.knoldus.service

import org.knoldus.dao.Dao
import org.knoldus.model.User
import java.util.UUID
// Implementation of UserService class to provide services to the user
class UserService(userDB: Dao[User]){
  def  createUser(user:User):Option[UUID]={
    userDB.create(user)
  }
  def getUserByID(id:Option[UUID]):User={
    userDB.getByID(id)
  }
  def updateUser(id:Option[UUID],updatedUser:User):Unit={
    userDB.update(id,updatedUser)
  }
  def deleteUser(id:Option[UUID]):Unit={
    userDB.delete(id)
  }
  def getAllUser:List[User] = userDB.getAll
}