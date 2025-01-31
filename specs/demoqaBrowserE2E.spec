# DemoQA Website E2E testing
Open Example Website and Verify Title
This scenario opens the example website, verifies the page title, clicks on a link, and checks for specific text on the new page.

## Setup

Tags: initiliaziation, Ui

* Navigate to DEMOQA page
* Verify page title "DEMOQA"
* Access "Elements" section
* Verify that I'm in "Elements" section

## Text Box Form Submission

Tags: textbox, smoke, Ui

* Click on "Text Box" option
* Fill in full name "John Doe"
* Fill in email "john.doe@example.com"
* Fill in current address "123 Current St"
* Fill in permanent address "456 Permanent Ave"
* Click submit button and verify form submission is successful
* Verify submitted name matches "John Doe"
* Verify submitted email matches "john.doe@example.com"

## Check Box Functionality

Tags: checkbox, Ui

* Click on "Check Box" option
* Expand all check box options
* Select "Documents" checkbox
* Verify "documents" folder is selected
* Verify that the sub-items "workspace" and "office" under "documents" are selected
* Deselect "Documents" checkbox
* Verify no items are selected

## Radio Button Selection

Tags: radio, Ui

* Click on "Radio Button" option
* Verify that the "Yes" radio button is "enabled"
* Select the "Yes" radio button
* Verify that the "Yes" radio button is "selected"
* Select the "Impressive" radio button
* Verify that the "impressive" radio button is "selected"
* Verify that the "No" radio button is "disabled"

## Web Tables Operations

Tags: tables, crud, Ui

* Click on "Web Tables" option
* Add new record and verify in table the following details:
  | First Name | Last Name | Email            | Age | Salary | Department |
  | Jane       | Smith     | jane@smith.com   | 28  | 5000   | QA         |
  | John       | Doe       | john@Doe.com     | 25  | 6000   | DEV        |
  | Andre      | Martinez  | Andre@Mart.com   | 21  | 8000   | HR         |
  | Vincent    | Lane      | john@Lane.com    | 22  | 10000  | DEV        |
  | Carlos     | Doe       | john@Joshway.com | 27  | 150000 | HR         |
* Search for "Jane"
* Ensure "Jane"'s "salary" to be "5000"
* Delete "Jane" record
* Verify record for "Jane" was deleted
* Search for "John"
* Ensure "John"'s "salary" to be "6000"
* Edit "John" record with new salary "15000"
* Ensure "John"'s "salary" to be "15000"
* Delete "John" record
* Verify record for "John" was deleted

## Button Click Actions

Tags: Button Interaction, UI

* Click on "Buttons" option
* Realize "Click" to Button
* Verify that the message "You have done a dynamic click" is displayed
* Realize "Double Click" to Button
* Verify that the message "You have done a double click" is displayed
* Realize "Right Click" to Button
* Verify that the message "You have done a right click" is displayed


## teardown

Tags: Shutdown

* Close browser







