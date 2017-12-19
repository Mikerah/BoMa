# BoMa

![Alt text](demo-app.gif?raw=true)

## Overview
BoMa is a semi-functional book manager that I need to manager my physical books. My university host book sales every year and I end up buying to many. This app to manage these books and help me decide which ones to keep.

## What I've learned
First, I learned how to setup a barcode scanner for the app using the Google Vision samples. I needed to retrieve the barcode of the books in order to look them up using the Google Books API. This was the longest part of the project since I had to change a lot of the code to fit my project.

Second, I learned how to integrate a database in an android app. I used SQLite as it is the simplest and easiest to get started with. I use some code samples from the Big Nerd Ranch book for this part since the book does a good job at explaining the code. 

Third, I learned more about the activity lifecycle and how to implement a lot of the UI functionality using activities instead of fragments. The activity lifecycle for Android apps is something that I struggle with and I decided to try to implement the UI for this project using activities so that I get a better sense of how it works.

## Improvements
First, I could deletion in the app. I didn't implement on the first go since I do not have a need for it. Second, I would try to improve the efficiency of fetching the Google Books API results and displaying the newly added book entry. At the moment, it takes about 30 seconds to scan a book and have it added to the list of books. Third, I would improve the UI functionality with Material design and a ViewPager for better viewing.
