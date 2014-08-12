
Scenario: check setValue in parallel work with Interactive Logic plugin in Survey

Given login as ivan
When create new survey SetValueInterLPlugin from existing survey
Then survey is added and SID not null

When added and setting InteractiveLogic plugin: display criteria Q1.A1 and show questions Q2
Then plugin added and configured success

When added and setting InteractiveLogic plugin: display criteria Q1.A2 and show questions Q3
Then plugin added and configured success

When open actions window
When create new set value: If Q1 A1 select logical condition selected (value: null) set value Set (value: null) for Q2 A1 C0
When create new set value: If Q1 A2 select logical condition selected (value: null) set value Set (value: null) for Q3 A1 C0
When close actions window

When get voting master Url
When go to voting

Then voting: is question Q2 visible equel false
Then voting: is question Q3 visible equel false

When voting (RC): select Q1 A1

Then voting: is question Q2 visible equel true
Then voting: is answer checked Q2 A1 equal true
Then voting: is question Q3 visible equel false

When voting (RC): select Q1 A2

Then voting: is question Q3 visible equel true
Then voting: is answer checked Q3 A1 equal true