
Scenario: create simple logic for Numeric in Survey and after voting copy this Survey and check

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

When delete survey


Scenario: create simple logic for SingleLine with value in Survey

Given login as ivan

When create new survey BranchingLogicSurveySingleLine from existing survey
Then survey is added and SID not null

When open actions window
When create new branching logic with value: after Q6 If Q6 A3 <logicalCondition> with value <logicValue> go to Q8
When close actions window
Then logical expression for Q6 visible on question list page and checked 'New page' after this expression

When get voting master Url
When go to voting

When voting: select Q6 A3 and type value1: <value1>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7

When click 'Back' button on VotingPage

When voting: select Q6 A3 and type value2: <value2>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7

When click 'Back' button on VotingPage

When voting: select Q6 A3 and type value3: <value3>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q8
Then finish check logic after voting: go to Q8

When delete survey

Examples:
|logicalCondition           |logicValue   |value1            |value2         |value3
|contain(s)                 |java         |javv              |gava           |java
|do(es) not contain         |testing      |testing with sel  |seltesting     |seltestin
|equal(s) to                |NewYork      |New York          |NewCity        |NewYork
|do(es) not equal to        |NewYork      |NewYork           |NewYork        |NewYork1
|contain(s) (RegEx)         |java\x20program |many use java progr |many use jav program |many use java program
|do(es) not contain (RegEx) |java\x20program |many use java program |java program use many |many use jav program
|equal(s) to (RegEx)        |many\x20use\x20java\x20program |many use java progr |many use program java |many use java program
|do(es) not equal to (RegEx)|many\x20use\x20java\x20program |many use java program |many use java program |many java program


Scenario: create simple logic for Pick one in Survey

Given login as ivan

When create new survey BranchingLogicSurveyPickOne from existing survey
Then survey is added and SID not null

When open actions window
When create new branching logic: after Q1 If Q1 A2 selected go to Q4
When close actions window
Then logical expression for Q1 visible on question list page and checked 'New page' after this expression

When get voting master Url
When go to voting

When voting (RC): select Q1 A1
When click 'Next' button on VotingPage
Then check logic after voting: go to Q2

When click 'Back' button on VotingPage

When voting (RC): select Q1 A3
When click 'Next' button on VotingPage
Then check logic after voting: go to Q2

When click 'Back' button on VotingPage

When voting (RC): select Q1 A2
When click 'Next' button on VotingPage
Then check logic after voting: go to Q4
Then finish check logic after voting: go to Q4

When delete survey


Scenario: create simple logic for Check All in Survey

Given login as ivan

When create new survey BranchingLogicSurveyCheckAll from existing survey
Then survey is added and SID not null

When open actions window
When create new branching logic: after Q3 If Q3 A5 selected go to Q10
When close actions window
Then logical expression for Q3 visible on question list page and checked 'New page' after this expression

When get voting master Url
When go to voting

When voting (RC): select Q3 A1
When click 'Next' button on VotingPage
Then check logic after voting: go to Q4

When click 'Back' button on VotingPage

When voting (RC): select Q3 A3
When click 'Next' button on VotingPage
Then check logic after voting: go to Q4

When click 'Back' button on VotingPage

When voting (RC): select Q3 A5
When click 'Next' button on VotingPage
Then check logic after voting: go to Q10
Then finish check logic after voting: go to Q10

When delete survey




