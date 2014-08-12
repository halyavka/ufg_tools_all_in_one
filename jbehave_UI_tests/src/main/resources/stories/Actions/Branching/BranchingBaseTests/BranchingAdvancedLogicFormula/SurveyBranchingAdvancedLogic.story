
Scenario: create advanced logic for Numeric in Survey

Given login as ivan

When create new survey BALogicSurveyNumeric from existing survey
Then survey is added and SID not null

When open actions window
When create new branching (advanced) logic: after question Q11 type logic expression Q11.A2 go to Q-1
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


Scenario: create advanced logic for SingleLine with value in Survey

Given login as ivan

When create new survey BALogicSurveySingleLine from existing survey
Then survey is added and SID not null

When open actions window
When create new branching (advanced) logic: after question Q6 type logic expression <logicExp> go to Q8
When close actions window
Then logical expression for Q6 visible on question list page and checked 'New page' after this expression

When get voting master Url
When go to voting

When voting: select Q6 A1 and type value1: <value1>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7

When click 'Back' button on VotingPage

When voting: select Q6 A1 and type value2: <value2>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q7

When click 'Back' button on VotingPage

When voting: select Q6 A1 and type value3: <value3>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q8
Then finish check logic after voting: go to Q8

When delete survey

Examples:
|logicExp                     |value1              |value2         |value3
|Q6.A1==LIKEjava              |javv                |gava           |java
|Q6.A1<>LIKEtesting           |testing with sel    |seltesting     |seltestin
|Q6.A1==NewYork               |New York            |NewCity        |NewYork
|Q6.A1<>NewYork               |NewYork             |NewYork        |NewYork1
|Q6.A1==RLIKEjava\x20program  |many use java progr   |many use jav program  |many use java program
|Q6.A1<>RLIKEjava\x20program  |many use java program |java program use many |many use jav program
|Q6.A1==RMATCHESmany\x20use\x20java\x20program |many use java progr   |many use program java |many use java program
|Q6.A1<>RMATCHESmany\x20use\x20java\x20program |many use java program |many use java program |many java program


Scenario: create advanced logic for Pick one in Survey

Given login as ivan

When create new survey BALogicSurveyPickOne from existing survey
Then survey is added and SID not null

When open actions window
When create new branching (advanced) logic: after question Q1 type logic expression Q1.A2 go to Q4
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


Scenario: create advanced logic for Check All in Survey

Given login as ivan

When create new survey BALogicSurveyCheckAll from existing survey
Then survey is added and SID not null

When open actions window
When create new branching (advanced) logic: after question Q3 type logic expression Q3.A5 go to Q6
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
Then check logic after voting: go to Q6
Then finish check logic after voting: go to Q6

When delete survey




