object Main extends App {
  var sqlConnection : SqlConnection = new SqlConnection()
  var foodArrayBuffer = sqlConnection.getCaloriesPerDollar
  val sortedFood = foodArrayBuffer.sortBy(_.caloriesPerDollar)
  for (f <- sortedFood) {
    println("Food item: " + f.item + ", calories per dollar = " + f.caloriesPerDollar)
  }
}
