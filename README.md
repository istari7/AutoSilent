# AutoSilent
Android Application that allows user to customize their ringer settings either based on either location or time.

```
Minimum Api Level - Lollipop (5.1)
Maximum Api Level - Pie      (9.0)
```
# Installation
1. Clone the git repository
    ```
    https://github.com/istari7/QuickkSilent.git
    ```
2. Open the folder in Android Studio and complete the gradle sync. Gradle will take care to download all the dependencies

3. Create you own account on Google Firebase, add your project id and replace the maps_api_key that you get in the google_api_key.xml

4. Debug the application to execute 
# Pre Requisites
A working knowledge of these will help you out a lot more and make the program more easier to read:

1. Google Firebase
2. Google Location API


# Important Note:
The project utilises Google Web API's extensively and several key components will be redacted later on to prevent key leaks. Since this is a demo for now, all the files have been included. As components are removed. I shall include a tutorial to code those components from scratch.

# Key Screenshots
##  1. Time Controlled Ringer Settings

 ![Image of Time Activity](https://github.com/istari7/QuickkSilent/blob/FInal-iter-1/time.png)

**Here based on the time ranger set. The phone gets silent in the time and once the timer expires,the phone is then reverted to normal mode**



## 2. Location Controlled Ringer Settings

![Image of Location Activity](https://github.com/istari7/QuickkSilent/blob/FInal-iter-1/location.png)


**Here once a paticular location is marked on the map. The location name is displayed to the user and the location is added to the database later on if  the add geofence button is clicked**


## 3. Firebase List in the App

![Image of Firebase List](https://github.com/istari7/QuickkSilent/blob/FInal-iter-1/firebase_list.png)
**Here the lcoation is extracted from the Google Firebase and geofences are active. If a person comes within a 400m radius of the location, the phone becomes silent. Once the user exits the location, the phone becomes normal again.**

## 4. Firebase Data Model

![Image of Firebase List](https://github.com/istari7/QuickkSilent/blob/FInal-iter-1/data_firebase.png)
This is a **SAMPLE** example of how i have organised the data.

# Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
For those who would like to discuss further development, my email is
```
sydtrq7@gmail.com
```

Also i am aware that the application is yet to be properly optimized and the tests to be written. I will update the application soon along with all the appropiate features that i want to integrate in the application in future.

# License
[MIT](https://choosealicense.com/licenses/mit/)
