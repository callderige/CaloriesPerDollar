import java.sql.{Connection, DriverManager}
import scala.collection.mutable.ArrayBuffer

class SqlConnection (url: String, driver: String, username: String, password: String) {
  def this() {
    this("jdbc:mysql://localhost:3306/db", "com.mysql.jdbc.Driver", "root", "")
  }

  class FoodItem {
    var item : String = _
    var calories : Int = _
    var price : Double = _
    var caloriesPerDollar : Double = _
  }

  def getCaloriesPerDollar: ArrayBuffer[FoodItem] = {
    var connection : Connection = null
    var allFood = ArrayBuffer[FoodItem]()

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      val rs = statement.executeQuery("SELECT * FROM mcdonalds_food")

      while (rs.next) {
        var f = new FoodItem
        f.item = rs.getString("item")
        f.calories = rs.getInt("calories")
        f.price = rs.getDouble("price")
        f.caloriesPerDollar = BigDecimal(f.calories/f.price).setScale(3, BigDecimal.RoundingMode.HALF_UP).toDouble
        allFood += f
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }

    connection.close()
    allFood
  }
}
