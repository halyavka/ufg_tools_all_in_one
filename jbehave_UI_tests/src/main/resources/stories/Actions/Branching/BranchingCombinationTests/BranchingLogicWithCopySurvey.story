
Scenario: create simple logic for Numeric in Survey created by copying

Given login as ivan

When create new survey BranchingLogicSurveyNumeric from existing survey
Then survey is added and SID not null

When open actions window
When create new branching logic: after Q11 If Q11 A2 selected go to Q-1
When close actions window
Then logical expression for Q11 visible on question list page and checked 'New page' after this expression

When get voting master Url
When go to voting

When voting: select Q11 A1 and type value: 11
When click 'Next' button on VotingPage
Then check logic after voting: go to Q12

When click 'Back' button on VotingPage

When voting: select Q11 A4 and type value: 34
When click 'Next' button on VotingPage
Then check logic after voting: go to Q12

When click 'Back' button on VotingPage

When voting: select Q11 A2 and type value: 23
When click 'Next' button on VotingPage
Then check logic after voting: go to Q-1
Then finish check logic after voting: go to Q-1

When copy survey with reports (false) and with responses (false)

Then logical expression for Q11 visible on question list page and checked 'New page' after this expression

When get voting master Url
When go to voting

When voting: select Q11 A1 and type value: 11
When click 'Next' button on VotingPage
Then check logic after voting: go to Q12

When click 'Back' button on VotingPage

When voting: select Q11 A4 and type value: 34
When click 'Next' button on VotingPage
Then check logic after voting: go to Q12

When click 'Back' button on VotingPage

When voting: select Q11 A2 and type value: 23
When click 'Next' button on VotingPage
Then check logic after voting: go to Q-1
Then finish check logic after voting: go to Q-1

When delete survey