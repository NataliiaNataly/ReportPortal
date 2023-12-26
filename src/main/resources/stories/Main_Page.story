Lifecycle:
Before:
Given User opens the 'https://rp.epam.com/ui/#login' web site
When User logs in with the next data:
| login               | password |
| DefaultPersonalUser | 1q2w3e4r |
And User navigates back
And On Log In page, user clicks on the 'Login' button

Scenario: 1.Check that the number of runs is correctly displayed in the table
Meta:
!-- @to_run
Then On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
When On Main RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab
And On Main RP page in Project Selector dropdown, user selects 'defaultpersonaluser_personal' project
And On Main RP page, user clicks on the 'LAUNCHES' tab
Then On Main RP page, user should see the table with runs
And On Main RP page, user verifies that the number of rows in the table is equal to value in counter

Scenario: 2.Check that the user can add the new filter for launch
Meta:
!-- @to_run
Then On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
When On Main RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab
And On Main RP page in Project Selector dropdown, user selects 'defaultpersonaluser_personal' project
And On Main RP page, user clicks on the 'LAUNCHES' tab
And On Main RP page, user clicks on the 'Add filter' button
And On Main RP page in 'Launch Name' input, user enters the 'random value' value
And On Main RP page, user clicks on the 'Save' button
And On the 'ADD FILTER' modal, user fills in the name input field with random value and saves it to DataHolder
And On the 'ADD FILTER' modal, user clicks on the 'Add' button
And User waits some time until page is loaded
Then On Main RP page on the header, user should see just created filter with name from DataHolder
When On Main RP page on the header, user deletes just created filter

Scenario: 3.Check that the user can add a few new filters for launch
Meta:
!-- @to_run
Then On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
When On Main RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab
And On Main RP page in Project Selector dropdown, user selects 'defaultpersonaluser_personal' project
And On Main RP page, user clicks on the 'LAUNCHES' tab
And On the Main page, user deletes all filters from UI
And User creates '<number>' new launch filters and saves their names to DataHolder
And User waits some time until page is loaded
Then User verifies that newly created and saved to DataHolder filters are all displayed on UI
When User deletes all newly created filters that were saved to DataHolder
And User logs out

Examples:
| number |
| 2      |
| 1      |
| 3      |

Scenario: 3.Check the 'ALL LAUNCHES' results table.
Meta:
!-- @to_run
Then On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
When On Main RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab
And On Main RP page in Project Selector dropdown, user selects 'defaultpersonaluser_personal' project
And On Main RP page, user clicks on the 'LAUNCHES' tab
When User waits some time until page is loaded
Then On Main RP page in 'LAUNCHES' results table, user should see the following data:
| name             | start               | total | passed | failed | skipped |
| Demo Api Tests#5 | 2023-11-06 14:24:36 | 30    | 30     |        |         |
| Demo Api Tests#4 | 2023-11-06 14:24:33 | 25    | 20     | 5      |         |
| Demo Api Tests#3 | 2023-11-06 14:24:31 | 20    | 10     | 8      | 2       |
| Demo Api Tests#2 | 2023-11-06 14:24:28 | 15    | 5      | 9      | 1       |
| Demo Api Tests#1 | 2023-11-06 14:24:27 | 10    | 1      | 9      |         |