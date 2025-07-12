package com.example.inventory

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.ItemDao
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.After
import java.io.IOException
import kotlin.jvm.Throws
import com.example.inventory.data.Item
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase
    //Implementar alguns dados
    private var item1 = Item(1, "Apples", 10.0, 20)
    private var item2 = Item(2, "Bananas", 15.0, 97)

    @Before
    fun criarDb(){
        val context: Context = ApplicationProvider.getApplicationContext()
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context,inventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.itemDao()
    }
    @After
    @Throws(IOException::class)
    fun fechaDb(){
        inventoryDatabase.close()
    }

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }
    private suspend fun addTwoItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }

    @Test
    @Throws(Exception::class)
    fun inserirDadosDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0],item1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
        assertEquals(allItems[1], item2)
    }

    //Testar o Update
    @Test
    @Throws(Exception::class)
    fun AtualizarItensDB() = runBlocking {
        addTwoItemsToDb()
        itemDao.update(Item(1, "Apples", 15.0, 25))
        itemDao.update(Item(2, "Bananas", 5.0, 50))

        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], Item(1, "Apples", 15.0, 25))
        assertEquals(allItems[1], Item(2, "Bananas", 5.0, 50))
    }

    //Testar o delete
    @Test
    @Throws(Exception::class)
    fun DeletarItens() = runBlocking {
        addTwoItemsToDb()
        itemDao.delete(item1)
        itemDao.delete(item2)

        val allItems = itemDao.getAllItems().first()
        assertTrue(allItems.isEmpty())
    }
    @Test
    @Throws(Exception::class)
    fun daoGetItem_returnsItemFromDB() = runBlocking {
        addOneItemToDb()
        val item = itemDao.getItem(1)
        assertEquals(item.first(), item1)
    }
}