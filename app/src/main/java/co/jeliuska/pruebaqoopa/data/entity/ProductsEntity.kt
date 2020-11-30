package co.jeliuska.pruebaqoopa.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "products"
)
data class ProductsEntity(

    var productName: String,
    var date: String,
    var productsDescription: String,
    var image: String?
) {

    @PrimaryKey(autoGenerate = true)
    var idProduct: Long = 0

}