package co.jeliuska.pruebaqoopa.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.jeliuska.pruebaqoopa.data.entity.ProductsEntity
import co.jeliuska.pruebaqoopa.data.local.dao.ProductsDao

@Database(
    entities = [
        ProductsEntity::class,
    ], version = 1, exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {


    abstract fun productsDao(): ProductsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "products-qoopa")
                .addMigrations(

                )
                .build()
        }
    }
}

