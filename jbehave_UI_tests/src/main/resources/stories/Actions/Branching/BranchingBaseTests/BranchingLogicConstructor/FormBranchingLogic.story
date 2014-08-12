
Scenario: create simple logic for SingleLine in Form

Given login as ivan

When create form with question on new page BranchingFormLogicSingleLine from existing form
Then form is added and SID not null

When open actions window
When create new branching logic for Form with value: after Page1 If Q1 A2 <logicalCondition> with value <logicValue> go to Page4
When close actions window

When get voting master Url
When go to voting

When voting: select Q1 A2 and type value1: <value1>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q2

When click 'Back' button on VotingPage

When voting: select Q1 A2 and type value2: <value2>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q2

When click 'Back' button on VotingPage

When voting: select Q1 A2 and type value3: <value3>
When click 'Next' button on VotingPage
Then check logic after voting: go to Q4
Then finish check logic after voting: go to Q4

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


Scenario: create simple logic for Pick one in Form

Given login as ivan

When create form with question on new page BranchingFormLogicPickOne from existing form
Then form is added and SID not null

When open actions window
When create new branching logic for form: after Page2 If Q2 A3 selected go to Page6
When close actions window

When get voting master Url
When go to voting

When click 'Next' button on VotingPage

When voting (RC): select Q2 A2
When click 'Next' button on VotingPage
Then check logic after voting: go to Q3

When click 'Back' button on VotingPage

When voting (RC): select Q2 A4
When click 'Next' button on VotingPage
Then check logic after voting: go to Q3

When click 'Back' button on VotingPage

When voting (RC: select Q2 A3
When click 'Next' button on VotingPage
Then check logic after voting: go to Q3
Then finish check logic after voting: go to Q3

Scenario: create simple logic for Check All in Survey

Given login as ivan

When create form with question on new page BranchingFormLogicCheckAll from existing form
Then form is added and SID not null

When open actions window
When create new branching logic for form: after Page3 If Q3 A5 selected go to Page8
When close actions window

When get voting master Url
When go to voting

When click 'Next' button on VotingPage

When click 'Next' button on VotingPage

When voting (RC): select Q3 A2
When click 'Next' button on VotingPage
Then check logic after voting: go to Q4

When click 'Back' button on VotingPage

When voting (RC: select Q3 A4
When click 'Next' button on VotingPage
Then check logic after voting: go to Q4

When click 'Back' button on VotingPage

When voting (RC): select Q3 A5
When click 'Next' button on VotingPage
Then check logic after voting: go to Q8
Then finish check logic after voting: go to Q8