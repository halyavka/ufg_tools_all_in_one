
Scenario: create advanced group logic for Numeric, PickOne and CheckAll in Survey

Given login as ivan

When create new survey BAGroupLogicSurveyNumeric from existing survey
Then survey is added and SID not null

When set allow multiple responses option

When open actions window
When create new branching (advanced) logic: after question Q6 type logic expression ( Q1.A1 OR Q3.A1 AND Q3.A2 AND Q4.A3 ) OR Q5.A2 go to Q9
When close actions window
Then logical expression for Q6 visible on question list page and checked 'New page' after this expression

When get voting master Url
When go to voting

When voting (ListBox): select Q5 A1
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7

When click 'Back' button on VotingPage

When voting (ListBox): select Q5 A2
When click 'Next' button on VotingPage
Then finish check logic after voting: go to Q9

When go to voting

When voting (ListBox): select Q5 A3
When voting (RC): select Q1 A2
When voting (RC): select Q3 A1
When voting (RC): select Q3 A2
When voting (DropDown): select Q4 A3
When click 'Next' button on VotingPage
Then finish check logic after voting: go to Q9

When click 'Back' button on VotingPage

When voting (RC): select Q1 A1
When voting (RC): unselect Q3 A1
When voting (RC): unselect Q3 A2
When click 'Next' button on VotingPage
Then finish check logic after voting: go to Q9

When click 'Back' button on VotingPage

When voting (RC): select Q1 A3
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7

When click 'Back' button on VotingPage

When voting (RC): select Q3 A1
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7

When click 'Back' button on VotingPage

When voting (RC): select Q3 A2
When click 'Next' button on VotingPage
Then finish check logic after voting: go to Q9

When delete survey