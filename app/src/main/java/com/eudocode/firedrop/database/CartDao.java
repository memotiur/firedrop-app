package com.eudocode.firedrop.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.eudocode.firedrop.utils.Util;

import java.util.List;

@Dao
public interface CartDao {
    @Query("SELECT * FROM cart "+ Util.TABLE_NAME_NOTE)
    List<Cart> getAll();


    /*
     * Insert the object in database
     * @param cart, object to be inserted
     */
    @Insert
    void insertCart(Cart cart);

    /*
     * update the object in database
     * @param cart, object to be updated
     */
    @Update
    void update(Cart repos);

    /*
     * delete the object from database
     * @param cart, object to be deleted
     */
    @Delete
    void delete(Cart cart);

    /*
     * delete list of objects from database
     * @param cart, array of objects to be deleted
     */
    @Delete
    void delete(Cart... cart);      // Cart... is varargs, here cart is an array

}
