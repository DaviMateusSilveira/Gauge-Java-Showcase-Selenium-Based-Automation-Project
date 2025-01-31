# DemoQA BookStore API Testing

The authentication for the API is handled by the `@beforeScenario` hook on commonUtils --> ApiUtils

## Add Book to User Collection

Tags: Positive, Create, Api

* Make a POST request to "/BookStore/v1/Books" with userId "9bd101c2-91c1-40e3-9d92-c2f9af859de1" and ISBN "9781449325862"
* Verify the response status code is "201"

## Get Book Details

Tags: Positive, Read, Api

* Make a GET request to "/BookStore/v1/Book/" with ISBN "9781449325862"
* Verify the response status code is "200"
* Verify the response contains the book title "Git Pocket Guide"

## Replace Book in User's Collection

Tags: Positive, Update, Api

* Make a PUT request to "/BookStore/v1/Books/{ISBN}" with userId "9bd101c2-91c1-40e3-9d92-c2f9af859de1", with ISBN "9781449325862" and new ISBN "9781449337711"
* Verify the response status code is "200"
* Verify the response contains the book title "Designing Evolvable Web APIs with ASP.NET"

## Remove Book from User's Collection

Tags: Positive, Delete, Api

* Make a DELETE request to "/BookStore/v1/Book" with userId "9bd101c2-91c1-40e3-9d92-c2f9af859de1" and ISBN "9781449337711"
* Verify the response status code is "204"

## Attempt to Add a Book with an Invalid ISBN

Tags: Negative, Create, Api

* Make a POST request to "/BookStore/v1/Books" with userId "9bd101c2-91c1-40e3-9d92-c2f9af859de1" and ISBN "5677772227711"
* Verify the response status code is "400"
* Verify the response contains the message "ISBN supplied is not available in Books Collection!"

## Attempt to Retrieve Book Details for a Non-Existent ISBN

Tags: Negative, Read, Api

* Make a GET request to "/BookStore/v1/Book/" with ISBN "9999999999999"
* Verify the response status code is "400"
* Verify the response contains the message "ISBN supplied is not available in Books Collection!"

## Attempt to Replace a Book with an Invalid User ID

Tags: Negative, Update, Api

* Make a PUT request to "/BookStore/v1/Books/{ISBN}" with userId "5cr601c2-81c1-20e3-1d92-g2f0af859de1", with ISBN "9781449325862" and new ISBN "9781449337711"
* Verify the response status code is "401"
* Verify the response contains the message "User Id not correct!"

## Attempt to Remove a Book That Does Not Exist in User’s Collection

Tags: Negative, Delete, Api

* Make a DELETE request to "/BookStore/v1/Book" with userId "9bd101c2-91c1-40e3-9d92-c2f9af859de1" and ISBN "1234567890123"
* Verify the response status code is "400"
* Verify the response contains the message "ISBN supplied is not available in User's Collection!"

##Add Book Without Token

Tags: Negative, Security, Api

* Make a POST request to "/BookStore/v1/Books" with userId "9bd101c2-91c1-40e3-9d92-c2f9af859de1" and ISBN "9781449325862" without the Authorization header
* Verify the response status code is "401"
* Verify the response contains the message "User not authorized!"

## Replace Book Without Token

Tags: Negative, Security, Api

* Make a PUT request to "/BookStore/v1/Books/{ISBN}" with userId "9bd101c2-91c1-40e3-9d92-c2f9af859de1", with ISBN "9781449325862" and new ISBN "9781449337711" without the Authorization header
* Verify the response status code is "401"
* Verify the response contains the message "User not authorized!"

## Attempt to Register a Book Using Another User’s ID

Tags: Negative, Security, Api

* Make a POST request to "/BookStore/v1/Books" with userId "df93d003-db2f-4132-a77e-f3173ddd07b6" and ISBN "9781449325862"
* Verify the response status code is "401"
* Verify the response contains the message "User not authorized!"