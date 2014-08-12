
Scenario: create setValue for PickOne, ChechAll, DropDown, ListBox, SingleLine in Survey

Given login as ivan

When create new survey SetValueSelected from existing survey
Then survey is added and SID not null

When open actions window
When create new set value: If Q1 A1 select logical condition selected (value: null) set value Set (value: null) for Q3 A5 C0
When create new set value: If Q3 A2 select logical condition selected (value: null) set value Clear (value: null) for Q2 A2 C0
When create new set value: If Q2 A2 select logical condition not selected (value: null) set value Set (value: null) for Q4 A2 C0
When create new set value: If Q5 A1 select logical condition selected (value: null) set value Set (value: null) for Q5 A5 C0
When create new set value: If Q3 A1 select logical condition selected (value: null) set value Set (value: AllIsFine) for Q6 A1 C0
When close actions window

When get voting master Url
When go to voting

When voting (RC): select Q2 A2
When voting (RC): select Q1 A1
When voting (RC): select Q3 A2
When voting (ListBox): select Q5 A1

Then voting: is answer checked Q3 A5 equal true
Then voting: is answer checked Q2 A2 equal false
Then voting: is selected dropDown or listBox Q4 A2 equal true
Then voting: is selected dropDown or listBox Q5 A5 equal true

When voting (RC): select Q3 A1

Then voting: actual text of Q6 A1 equal true with expected text AllIsFine


Scenario: create setValue for RateDifferent, Matrix, SingleLine, Numeric in Survey

Given login as ivan

When create new survey SetValueR_M_N from existing survey
Then survey is added and SID not null

When set new page after question Q8

When open actions window
When create new set value: If Q4 A3 select logical condition selected (value: null) set value Set (value: testingselenium) for Q6 A3 C0
When create new set value: If Q6 A3 select logical condition contain(s) (value: selenium) set value Set (value: 23) for Q11 A2 C0
When create new set value: If Q6 A3 select logical condition equal(s) to (value: testingselenium) set value Set (value: matrixValue) for Q10 A2 C3
When create new set value: If Q11 A1 select logical condition equal(s) to (value: 12) set value Clear (value: null) for Q4 A3 C0
When create new set value: If Q6 A1 select logical condition selected (value: null) set value Set (value: null) for Q9 A3 C5
When close actions window

When get voting master Url
When go to voting

When voting (DropDown): select Q4 A3
When voting: select Q6 A1 and type value: test

When click 'Next' button on VotingPage

Then voting: is answer checked of RateDifferent Q9 A3 C5 equal true
Then voting: actual value of Matrix answer Q10 A2 C3 equal true with expected text matrixValue
Then voting: actual text of Q11 A2 equal true with expected text 23

When voting: select Q11 A1 and type value: 12

When click 'Back' button on VotingPage

Then voting: is selected dropDown or listBox Q4 A3 equal false

When delete survey



