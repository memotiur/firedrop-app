package com.eudocode.firedrop.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cart {

    @PrimaryKey(autoGenerate = true)
    private int cart_id;

    @ColumnInfo(name = "cart_content") // column name will be "cart_content" instead of "content" in table
    private String content;
    private String title;


    public Cart(int cart_id, String content, String title) {
        this.cart_id = cart_id;
        this.content = content;
        this.title = title;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;

        Cart cart = (Cart) o;

        if (cart_id != cart.cart_id) return false;
        return title != null ? title.equals(cart.title) : cart.title == null;
    }



    @Override
    public int hashCode() {
        int result = cart_id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart_id=" + cart_id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
