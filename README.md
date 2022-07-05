**Selenium scraper application developed to automate checking the processing time of certain application then notify by email once values are updated.**

:one: The Application will use Selenium with ChromeDriver.exe to 
  - Access the webpage
  - Fill the dropdownlists (3 of them)
  - Click on submit button. 
  - Read the result that consists of two values: Processing time (what we are actually looking for) and Last Update Date (what we need to know if the previous processing   time was updated or not)  
 
:two: The Application will store the first read value in the H2 Database, in Result table more precisly. 

:three: A scheduler is set to re-scrap every [x] seconds. The next read values (after the first values) will be examined as follow:
  - If the last updated date did not change, nothing will happen.
  - If the last updated date changed, an email will be sent and the table Result will be updated. 
  



:warning: Notes:
- Fill the application.properties with missing values. (Need to login to gmail in order to generate a password)
- Fill the application.yml with missing values.
- Launch h2 locally: http://localhost:8080/h2-console
