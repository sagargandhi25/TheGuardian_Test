![screenshot](https://user-images.githubusercontent.com/58938625/98558576-82869500-229d-11eb-8005-201f6320ff79.png)

# TheGuardian News
## About
<p>It simply loads News Articles from API and stores it in persistence storage (i.e SQLite Database). Articles will be always loaded from local database. 
  Remote data(from API) and local data is always synchronized.</p>
  <ul>
  <li>This makes it offline capable 😃.</li>
  </ul>
  
  ## Project Structure
#### Coroutines
<ul>
  <li>|--viewmodel -> ArticleViewModel -Used Coroutines launch for call API service and insert data into local database in a background thread. </li>
  <li>|--MyApplication - To start Workmanager job and run in the background thread.</li>
  </ul>
  
#### ---Android Architecture Components---

  #### LiveData 
  
  <ul>
  <li>|--viewmodel ->ArticleViewModel- LiveData has been used for list, it observes the items and update the displayed items when result chnage
    It has been used for observe loading set and display progress bar</li>
  </ul>
  
  #### ViewModel 
  <ul>
  <li>|--viewmodel ->ArticleViewModel - It is preparing and managing data for display Article list. </li>

  </ul>
  
  #### Workmanager
  <ul>
  <li>|--background ->SyncDatabaseWM - Workmanager has been used here for periodically syncing application data with server on a background thread.</li>
  </ul>
  
  #### ViewBinding/DataBinding 
  <ul>
  <li>|--ArticleFragment - ViewBinding has been used here for resource layout and recyclerView.</li>
  <li>Databinding has been used for bind UI components of row_article.xml with data source.</li>
  </ul>
  
  #### Room 
  <ul>
  <li>|--database -> DatabaseArticle - Created @Entity for Room Database with expected fields.</li>
  <li>|--database -> ArticleDao - Created @Ddatabse &  @Dao which contains all database related queries to manage data with database.
  </ul>
  
  #### Koin
  <ul>
  <li>|--di -> Use Koin to perform API Service module injection, database module injection, network module injection, repository module injection and viewmodel module injection.</li>
  </ul>
  
#### Retrofit 
<ul>
  <li>|--di -> NetworkModule - Retrofit has been used to perform network calls</li>
  </ul>
  
#### MOSHI
<ul>
  <li>|--di -> NetworkModule - Moshi has been used for json data parsing.</li> 
  </ul>
  
#### Glide 
<ul>
  <li>|--util ->BindingAdapters - Here, Glide image loading library has been used to load image into imageview.</li>
  </ul>
  
#### Material Components for Android 
<ul>
  <li>Material Cardview has been used to display Articles in RecyclerView. </li>
  </ul>
  
 #### Espresso 
 <ul>
  <li>Espresso has been used for instrumentation testing for check, is Activity Displayed?, is ArticleList_Fragment_Layout is visible?, is RecyclerView visible? progressbar is not showing when success.
  </ul>
  
#### Mockito
<ul>
  <li>Mockito has been used for implement Unit Test cases and mock the objects.Test cases has been written for viewmodel to check - when viewmodel is created it gets the data correctly, check for loading state. </li>
  </ul>


## Built With 🛠
<p> Kotlin - First class and official programming language for Android development.</p>
<p>Coroutines - For concurrency and multithreading</p>
<p>Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.</p>
<ul>
  <li>LiveData - Data objects that notify views when the underlying database changes.</li>
  <li>ViewModel - Stores UI-related data that it's survive configuration changes.</li>
  <li>Workmanager - It is for background work that's deferrable and requires guranteed execution.</li>
  <li>ViewBinding - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.</li>
  <li>Room - SQLite object relational mapping library.</li>
  </ul>
<p>Koin - It's a lightweight dependency injection framework for kotlin.<p>
<p>Retrofit - A type-safe HTTP client for Android and Java.</p>
<p>Moshi - Moshi is a modern JSON library for Android and Java. It makes it easy to parse JSON into Java objects.<p>
<p>Glide - An image loading library for Android </p>
<p>Material Components for Android - Modular and customizable Material Design UI components for Android.</p>
<p>Espresso - Espresso is a Testing Framework for Android to make it easy to write reliable user inteface tests.</p>
<p>Mockito - Mockito is a popular mocking library for clean and readable unit tests. 

## Architecture
<p>This app uses MVVM (Model View View-Model) architecture.</p>

![MVVM](https://user-images.githubusercontent.com/58938625/98557626-59b1d000-229c-11eb-9be3-44115ecd55ba.png)


