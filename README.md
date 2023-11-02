# HungryTime
HungryTime is an app that makes making food easier and gives you full information about the selected meal including instructions of how to make that meal provided by a video instruction on YouTube.

<table>
  <tr>
    <td>Home Page</td>
     <td>Favourite </td>
     <td>Category </td>
  </tr>
  <tr>
    <td valign="top"><img src="Screenshot_20230402_193845_Hungry Time.jpg" width="220" height="400"></td>
    <td valign="top"><img src="Screenshot_20230402_193849_Hungry Time.jpg" width="220" height="400"></td>
    <td valign="top"><img src="Screenshot_20230402_193853_Hungry Time.jpg" width="220" height="400"></td>
    
  </tr>
 </table>

 <table>
  <tr>
     <td>Meal Details </td>
     <td>Meals in Categories </td>
     <td>Search Page </td>
  </tr>
  <tr>
    <td valign="top"><img src="Screenshot_20230402_193859_Hungry Time.jpg" width="220" height="400"></td>
    <td valign="top"><img src="Screenshot_20230402_193919_Hungry Time.jpg" width="220" height="400"></td>
    <td valign="top"><img src="Screenshot_20230402_193934_Hungry Time.jpg" width="220" height="400"></td>
    
  </tr>
 </table>


 # Libraries and technologies used
- Navigation component : one activity contains multiple fragments instead of creating multiple activites.
- Retrofit : making HTTP connection with the rest API and convert meal json file to Kotlin/Java object.
- Room : Save meals in local database.
- MVVM & LiveData : Saperate logic code from views and save the state in case the screen configuration changes.
- Coroutines : do some code in the background.
- view binding : instead of inflating views manually view binding will take care of that.
- Glide : Catch images and load them in imageView.

