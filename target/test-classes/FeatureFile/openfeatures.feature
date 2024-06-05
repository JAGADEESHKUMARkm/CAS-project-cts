Feature: Trutime application
Scenario: Validation of Trutime application
   Given user is navigating to Be.Cognizant website
   Then the user gets verified
   When One Cognizant is present
   Then One Cognizant should be clicked
   When Trutime is typed on the search bar
   When Trutime is clicked 
   Then Trutime is navigated
   When Legends are printed
   And the current date is highlighted
   And the user checks whether the trutime and the system dates are equal
   And the date until when the topup can be done is checked
   Then the current month and year is checked