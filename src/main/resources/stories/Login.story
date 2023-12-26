Scenario: 1.Check that the user can log in to ReportPortal
Meta:
!-- @to_run
Given User opens the 'https://rp.epam.com/ui/#login' web site
When User logs in with the next data:
| login               | password |
| DefaultPersonalUser | 1q2w3e4r |
And User navigates back
And On Log In page, user clicks on the 'Login' button
Then On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
